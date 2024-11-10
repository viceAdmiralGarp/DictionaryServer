package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.topics.subtopic.SubTopicDto;
import com.mmdev.dictionaryy.repository.SubTopicRepository;
import com.mmdev.dictionaryy.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTopicService {

	private final SubTopicRepository subTopicRepository;
	private final TopicRepository topicRepository;

	public List<SubTopicDto> getAllSubTopic() {
		return subTopicRepository.findAll().stream()
				.map(SubTopic::toDto)
				.collect(Collectors.toList());//TODO toList()
	}

	public SubTopicDto getSubTopicById(Long id) {//TODO get and find name convention
		return subTopicRepository.findById(id)
				.map(SubTopic::toDto)
				.orElseThrow(() -> new EntityNotFoundException("SubTopic not found with id: " + id));
	}

	public SubTopicDto createSubTopic(SubTopicDto subTopicDto) {
		Topic topic = findTopicById(subTopicDto.topicId());
		SubTopic subTopic = subTopicDto.toSubTopic(topic);
		return subTopicRepository.save(subTopic).toDto();
	}

	public void updateSubTopicNameById(Long id, String name) {
		SubTopic subTopic = findSubTopicById(id);
		validateSubTopicName(name);
		subTopic.setName(name);
		subTopicRepository.save(subTopic);
	}

	public void updateSubTopicByTopicId(Long id, Long topicId) {
		SubTopic subTopic = findSubTopicById(id);
		Topic topic = findTopicById(topicId);
		subTopic.setTopic(topic);
		subTopicRepository.save(subTopic);
	}

	public void deleteSubTopic(Long id) {
		SubTopic subTopic = findSubTopicById(id);
		subTopicRepository.delete(subTopic);
	}

	private void validateSubTopicName(String name) {
		subTopicRepository.findTopicByName(name)//TODO existence
				.ifPresent(s -> {
					throw new EntityAlreadyRelatedException(
							"The SubTopic with this name " + name + " belongs to another SubTopic.");
				});
	}

	private SubTopic findSubTopicById(Long id) {
		return subTopicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("SubTopic not found with id: " + id));
	}

	private Topic findTopicById(Long id) {
		return topicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Topic not found with id: " + id));
	}
}
