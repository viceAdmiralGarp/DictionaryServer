package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.topics.subtopic.SubTopicDto;
import com.mmdev.dictionaryy.model.topics.subtopic.UpdateSubTopicNameDto;
import com.mmdev.dictionaryy.model.topics.subtopic.UpdateSubTopicSchoolByIdDto;
import com.mmdev.dictionaryy.service.SubTopicService;
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
@RequestMapping("/api/subtopics")
@RequiredArgsConstructor
public class SubTopicController {

	private final SubTopicService subTopicService;

	@GetMapping
	public ResponseEntity<List<SubTopicDto>> getAllTopics() {
		List<SubTopicDto> allSubTopics = subTopicService.getAllSubTopic();
		return ResponseEntity.ok(allSubTopics);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubTopicDto> getTopicById(@PathVariable Long id) {
		SubTopicDto topic = subTopicService.getSubTopicById(id);
		return ResponseEntity.ok(topic);
	}

	@PostMapping
	public ResponseEntity<SubTopicDto> createTopic(@RequestBody @Validated SubTopicDto subTopicDto) {
		SubTopicDto subTopic = subTopicService.createSubTopic(subTopicDto);
		return ResponseEntity.ok(subTopic);
	}

	@PutMapping("/{id}/name")
	public void updateTopicNameById(@PathVariable Long id, @RequestBody UpdateSubTopicNameDto subTopicDto) {
		subTopicService.updateSubTopicNameById(id, subTopicDto.name());
	}

	@PutMapping("/{id}/topic")
	public void updateTopicSchoolById(@PathVariable Long id, @RequestBody UpdateSubTopicSchoolByIdDto subTopicDto) {
		subTopicService.updateSubTopicByTopicId(id, subTopicDto.topicId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		subTopicService.deleteSubTopic(id);
		return ResponseEntity.ok().build();
	}

}
