package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

	private TopicService topicService;

	@GetMapping
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
		Topic topic = topicService.getTopicById(id);
		if (topic == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(topic);
	}

	@PostMapping
	public Topic createTopic(@RequestBody Topic topic) {
		return topicService.createTopic(topic);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Topic> updateTopic(
			@PathVariable Long id,
			@RequestBody Topic topic) {
		Topic updatedTopic = topicService.updateTopic(id, topic);
		return ResponseEntity.ok(updatedTopic);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.noContent().build();
	}
}
