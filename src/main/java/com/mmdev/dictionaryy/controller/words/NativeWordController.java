package com.mmdev.dictionaryy.controller.words;

import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.service.NativeWordService;
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
@RequestMapping("/api/native-words")
@RequiredArgsConstructor
public class NativeWordController {
	private final NativeWordService nativeWordService;

	@GetMapping
	public ResponseEntity<List<NativeWordDto>> getAllAdmins() {
		List<NativeWordDto> allNativeWords = nativeWordService.getAllNativeWords();
		return ResponseEntity.ok(allNativeWords);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NativeWordDto> getAdminById(@PathVariable Long id) {
		NativeWordDto nativeWordDto = nativeWordService.getNativeWordById(id);
		return ResponseEntity.ok(nativeWordDto);
	}

	@PostMapping
	public void createNativeWord(@RequestBody @Validated NativeWordDto NativeWordDto) {
		nativeWordService.creatNativeWord(NativeWordDto);
	}

	@PutMapping("/{id}/name")
	public void updateNativeWordById(@PathVariable Long id, @RequestBody @Validated String name) {
		nativeWordService.updateNativeWordById(id, name);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
		nativeWordService.deleteNativeWordById(id);
		return ResponseEntity.ok().build();
	}
}
