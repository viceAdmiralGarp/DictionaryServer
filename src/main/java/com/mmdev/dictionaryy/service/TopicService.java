package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.topics.topic.TopicDto;
import com.mmdev.dictionaryy.repository.topics.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

	private final TopicRepository topicRepository;
	private final SchoolService schoolService;

	public List<Topic> getAllTopics() {
		return topicRepository.findAll()
				.stream()
				.toList();
	}

	public Topic findTopicById(Long id) {
		return topicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
	}

	@Transactional
	public TopicDto createTopic(TopicDto topicDto) {
		validateTopicName(topicDto.name());
		School school = schoolService.findSchoolById(topicDto.schoolId());
		Topic topic = topicDto.toTopic(school);
		topicRepository.save(topic);
		return topic.toDto();
	}

	@Transactional
	public void deleteTopic(Long id) {
		Topic topic = findTopicById(id);
		topicRepository.delete(topic);
	}

	@Transactional
	public void updateTopicNameById(Long id, String name) {
		Topic topic = findTopicById(id);
		validateTopicName(name);
		topic.setName(name);
	}

	@Transactional
	public void updateTopicSchoolById(Long id, Long schoolId) {
		Topic topic = findTopicById(id);
		School school = schoolService.findSchoolById(schoolId);
		topic.setSchool(school);
	}

	public List<Topic> findTopicsBySchoolId(Long schoolId){
		return topicRepository.findTopicsBySchoolId(schoolId);
	}

	public void validateTopicName(String name) {
		if (topicRepository.existsTopicByName(name)) {
			throw new EntityAlreadyRelatedException(
					"The Topic with this name " + name + " belongs to another Topic.");
		}
	}
}
