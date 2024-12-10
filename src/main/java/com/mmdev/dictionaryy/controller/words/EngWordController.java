package com.mmdev.dictionaryy.controller.words;

import com.mmdev.dictionaryy.model.words.EngWordDto;
import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.service.words.EngWordService;
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
@RequestMapping("/api/eng-words")
@RequiredArgsConstructor
public class EngWordController {
	private final EngWordService engWordService;

	@GetMapping
	public ResponseEntity<List<EngWordDto>> getAllEngWords() {
		List<EngWordDto> allEngWords = engWordService.getAllEngWords();
		return ResponseEntity.ok(allEngWords);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EngWordDto> getEngWordById(@PathVariable Long id) {
		EngWordDto engWordDto = engWordService.getEngWordById(id);
		return ResponseEntity.ok(engWordDto);
	}

	@PostMapping
	public void createEngWord(@RequestBody @Validated EngWordDto engWordDto) {
		engWordService.createEngWord(engWordDto);
	}

	@PutMapping("/{id}/name")
	public void updateEngWordById(@PathVariable Long id, @RequestBody @Validated String name) {
		engWordService.updateEngWordById(id, name);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEng(@PathVariable Long id) {
		engWordService.deleteEngWordById(id);
		return ResponseEntity.ok().build();
	}
}

