package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.topics.subtopic.SubTopicDto;
import com.mmdev.dictionaryy.repository.topics.SubTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTopicService {

	private final SubTopicRepository subTopicRepository;
	private final TopicService topicService;

	public List<SubTopic> getAllSubTopic() {
		return subTopicRepository.findAll()
				.stream()
				.toList();
	}

	public SubTopic findSubTopicById(Long id) {
		return subTopicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("SubTopic not found with id: " + id));
	}

	@Transactional
	public SubTopicDto createSubTopic(SubTopicDto subTopicDto) {
		validateSubTopicName(subTopicDto.name());
		Topic topic = topicService.findTopicById(subTopicDto.topicId());
		SubTopic subTopic = subTopicDto.toSubTopic(topic);
		subTopicRepository.save(subTopic);
		return subTopic.toDto();
	}

	@Transactional
	public void deleteSubTopic(Long id) {
		SubTopic subTopic = findSubTopicById(id);
		subTopicRepository.delete(subTopic);
	}

	@Transactional
	public void updateSubTopicNameById(Long id, String name) {
		SubTopic subTopic = findSubTopicById(id);
		validateSubTopicName(name);
		subTopic.setName(name);
	}

	@Transactional
	public void updateSubTopicByTopicId(Long id, Long topicId) {
		SubTopic subTopic = findSubTopicById(id);
		Topic topic = topicService.findTopicById(topicId);
		subTopic.setTopic(topic);
	}

	public void validateSubTopicName(String name) {
		if (subTopicRepository.existsSubTopicByName(name))
			throw new EntityAlreadyRelatedException(
					"The SubTopic with this name " + name + " belongs to another SubTopic.");
	}
}
