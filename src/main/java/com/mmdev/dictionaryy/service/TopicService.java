package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

	private final TopicRepository topicRepository;

	public List<Topic> getAllTopics() {
		return topicRepository.findAll();
	}

	public Topic getTopicById(Long id) {
		return topicRepository.findById(id).orElse(null);
	}

	public Topic createTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	public Topic updateTopic(Long id, Topic topic) {
		topic.setId(id);
		return topicRepository.save(topic);
	}

	public void deleteTopic(Long id) {
		topicRepository.deleteById(id);
	}
}
