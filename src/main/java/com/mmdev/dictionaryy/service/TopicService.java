package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.topic.TopicDtoMapper;
import com.mmdev.dictionaryy.mapper.topic.TopicMapper;
import com.mmdev.dictionaryy.model.TopicDto;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import com.mmdev.dictionaryy.repository.TopicRepository;
import com.mmdev.dictionaryy.validator.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService implements EntityValidator<Topic, TopicDto> {

	private final TopicRepository topicRepository;
	private final SchoolRepository schoolRepository;
	private final TopicDtoMapper topicDtoMapper;
	private final TopicMapper topicMapper;

	public List<TopicDto> getAllTopics() {
		return topicRepository.findAll().stream()
				.map(topicDtoMapper::map)
				.collect(Collectors.toList());
	}

	public TopicDto getTopicById(Long id) {
		return topicRepository.findById(id)
				.map(topicDtoMapper::map)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
	}

	public TopicDto createTopic(TopicDto topicDto) {
		Topic topic = topicMapper.map(topicDto);
		Topic savedTopic = topicRepository.save(topic);
		return topicDtoMapper.map(savedTopic);
	}

	public TopicDto updateTopic(Long id, TopicDto topicDto) {
		Topic topic = entityValidator(id, topicDto);
		Topic savedTopic = topicRepository.save(topic);
		return topicDtoMapper.map(savedTopic);
	}

	public void deleteTopic(Long id) {
		Topic topic = topicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
		topicRepository.delete(topic);
	}

	@Override
	public Topic entityValidator(Long id, TopicDto dto) {
		Topic topic = topicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));

		if (topic.getName().equals(dto.name())) {
			throw new EntityAlreadyRelatedException(
					"The Topic name '" + dto.name() + "' is already in use by another Topic.");
		}

		topic.setName(dto.name());

		if (topic.getSchool() == null || !topic.getSchool().getId().equals(dto.schoolId())) {
			School school = schoolRepository.findById(dto.schoolId())
					.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + dto.schoolId()));

			Optional<Topic> optionalTopicBySchoolId = topicRepository.findBySchoolId(dto.schoolId());

			if (optionalTopicBySchoolId.isPresent() && !optionalTopicBySchoolId.get().getId().equals(id)) {
				throw new EntityAlreadyRelatedException(
						"The administrator with this ID " + dto.schoolId() + " belongs to another school.");
			}
			topic.setSchool(school);
		}
		return topic;
	}
}
