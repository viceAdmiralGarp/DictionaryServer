package com.mmdev.dictionaryy.service.words;

import com.mmdev.dictionaryy.entity.words.EngWord;
import com.mmdev.dictionaryy.entity.words.NativeWord;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.words.EngWordDto;
import com.mmdev.dictionaryy.model.words.NativeWordDto;
import com.mmdev.dictionaryy.repository.words.EngWordRepository;
import com.mmdev.dictionaryy.repository.words.NativeWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EngWordService {

	private final EngWordRepository engWordRepository;

	public List<EngWordDto> getAllEngWords() {
		return engWordRepository.findAll()
				.stream()
				.map(EngWord::toDto)
				.collect(Collectors.toList());
	}

	public EngWordDto getEngWordById(Long id) {
		return engWordRepository.findById(id)
				.map(EngWord::toDto)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}

	@Transactional
	public void deleteEngWordById(Long id) {
		EngWord engWord = findEngWordById(id);
		engWordRepository.delete(engWord);
	}

	@Transactional
	public void createEngWord(EngWordDto engWordDto) {
		EngWord engWord = engWordDto.toWord();
		engWordRepository.save(engWord);
	}

	@Transactional
	public void updateEngWordById(Long id, String name) {
		EngWord engWord = findEngWordById(id);
		engWord.setName(name);
	}

	private EngWord findEngWordById(Long id) {
		return engWordRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("EngWord not found with id: " + id));
	}
}
