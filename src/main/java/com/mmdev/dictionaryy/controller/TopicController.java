package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.school.SchoolDto;
import com.mmdev.dictionaryy.model.topic.TopicDto;
import com.mmdev.dictionaryy.model.topic.UpdateTopicNameDto;
import com.mmdev.dictionaryy.model.topic.UpdateTopicSchoolByIdDto;
import com.mmdev.dictionaryy.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

	private final TopicService topicService;

	@GetMapping
	public ResponseEntity<List<TopicDto>> getAllTopics() {
		List<TopicDto> allTopics = topicService.getAllTopics();
		return ResponseEntity.ok(allTopics);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDto> getTopicById(@PathVariable Long id) {
		TopicDto topic = topicService.getTopicById(id);
		return ResponseEntity.ok(topic);
	}

	@PostMapping
	public ResponseEntity<TopicDto> createTopic(@RequestBody @Validated TopicDto topicDto) {
		TopicDto topic = topicService.createTopic(topicDto);
		return ResponseEntity.ok(topic);
	}

	@PutMapping("/{id}/topic")
	public void updateTopicNameById(@PathVariable Long id, @RequestBody UpdateTopicNameDto topicDto) {
		topicService.updateTopicNameById(id, topicDto.name());
	}

	@PutMapping("/{id}/school")
	public void updateTopicSchoolById(@PathVariable Long id, @RequestBody UpdateTopicSchoolByIdDto topicDto) {
		topicService.updateTopicSchoolById(id, topicDto.schoolId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok().build();
	}
}
