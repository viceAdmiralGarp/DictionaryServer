package com.mmdev.dictionaryy.service.words;

import com.mmdev.dictionaryy.entity.words.NativeWord;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.repository.words.NativeWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void deleteNativeWordById(Long id) {
		NativeWord nativeWord = findNativeWordById(id);
		nativeWordRepository.delete(nativeWord);
	}

	@Transactional
	public void creatNativeWord(NativeWordDto nativeWordDto) {
		NativeWord word = nativeWordDto.toWord();
		nativeWordRepository.save(word);
	}

	@Transactional
	public void updateNativeWordById(Long id, String name) {
		NativeWord nativeWord = findNativeWordById(id);
		nativeWord.setName(name);
	}

	private NativeWord findNativeWordById(Long id) {
		return nativeWordRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("NativeWord not found with id: " + id));
	}
}
