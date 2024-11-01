package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.TopicDto;
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
	public List<TopicDto> getAllTopics() {
		return topicService.getAllTopics();
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

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok().build();
	}
}
