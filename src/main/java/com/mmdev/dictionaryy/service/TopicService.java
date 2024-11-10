package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.topics.topic.TopicDto;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import com.mmdev.dictionaryy.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

	private final TopicRepository topicRepository;//TODO use service layer
	private final SchoolRepository schoolRepository;

	public List<TopicDto> getAllTopics() {
		return topicRepository.findAll().stream()
				.map(Topic::toDto)
				.collect(Collectors.toList());
	}

	public TopicDto getTopicById(Long id) {
		return topicRepository.findById(id)
				.map(Topic::toDto)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
	}

	public TopicDto createTopic(TopicDto topicDto) {
		School school = findSchoolById(topicDto.schoolId());
		Topic topic = topicDto.toTopic(school);
		return topicRepository.save(topic).toDto();
	}

	public void deleteTopic(Long id) {
		Topic topic = findTopicById(id);
		topicRepository.delete(topic);
	}

	public void updateTopicSchoolById(Long id, Long schoolId) {
		Topic topic = findTopicById(id);
		School school = findSchoolById(schoolId);
		topic.setSchool(school);
		topicRepository.save(topic);
	}

	public void updateTopicNameById(Long id, String name) {
		Topic topic = findTopicById(id);
		validateTopicName(name);
		topic.setName(name);
		topicRepository.save(topic);
	}

	private School findSchoolById(Long id) {
		return schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
	}

	private Topic findTopicById(Long id) {
		return topicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
	}

	private void validateTopicName(String name) {
		topicRepository.findTopicByName(name)
				.ifPresent(s -> {
					throw new EntityAlreadyRelatedException(
							"The Topic with this name " + name + " belongs to another Topic.");
				});
	}
}
