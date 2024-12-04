package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.service.NativeWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
