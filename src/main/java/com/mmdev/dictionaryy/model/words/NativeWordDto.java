package com.mmdev.dictionaryy.model.words;

import com.mmdev.dictionaryy.entity.words.NativeWord;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NativeWordDto(
		Long id,
		@NotNull
		String name
) {
	public NativeWord toWord(){
		return NativeWord.builder()
				.name(name)
				.build();
	}
}
