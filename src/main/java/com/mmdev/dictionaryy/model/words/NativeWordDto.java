package com.mmdev.dictionaryy.model.words;

import com.mmdev.dictionaryy.entity.words.EngWord;
import lombok.Builder;

@Builder
public record NativeWordDto(
		Long id,
		String name
) {
	public NativeWordDto toWord(){
		return NativeWordDto.builder()
				.name(name)
				.build();
	}
}
