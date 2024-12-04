package com.mmdev.dictionaryy.model.words;

import com.mmdev.dictionaryy.entity.words.EngWord;
import lombok.Builder;

@Builder
public record EngWordDto(
		Long id,
		String name
) {
	public EngWord toWord(){
		return EngWord.builder()
				.name(name)
				.build();
	}
}
