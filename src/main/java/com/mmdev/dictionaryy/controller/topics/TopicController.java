package com.mmdev.dictionaryy.controller.topics;

import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.model.topics.topic.TopicDto;
import com.mmdev.dictionaryy.service.TopicService;
import jakarta.validation.constraints.NotNull;
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
		List<TopicDto> allTopics = topicService.getAllTopics()
				.stream()
				.map(Topic::toDto)
				.toList();
		return ResponseEntity.ok(allTopics);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDto> findTopicById(@PathVariable Long id) {
		TopicDto topic = topicService.findTopicById(id).toDto();
		return ResponseEntity.ok(topic);
	}

	@PostMapping
	public ResponseEntity<TopicDto> createTopic(@RequestBody @Validated TopicDto topicDto) {
		TopicDto topic = topicService.createTopic(topicDto);
		return ResponseEntity.ok(topic);
	}

	@PutMapping("/{id}/name")
	public void updateTopicNameById(@PathVariable Long id, @RequestBody String name) {
		topicService.updateTopicNameById(id, name);
	}

	@PutMapping("/{id}/school")
	public void updateTopicSchoolById(@PathVariable Long id,@NotNull Long schoolId) {
		topicService.updateTopicSchoolById(id, schoolId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok().build();
	}
}
