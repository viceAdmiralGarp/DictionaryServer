package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.words.NativeWord;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.repository.NativeWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NativeWordService {

	private final NativeWordRepository nativeWordRepository;

	public List<NativeWordDto> getAllNativeWords() {
		return nativeWordRepository.findAll()
				.stream()
				.map(NativeWord::toDto)
				.collect(Collectors.toList());
	}

	public NativeWordDto getNativeWordById(Long id) {
		return nativeWordRepository.findById(id)
				.map(NativeWord::toDto)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}
}
