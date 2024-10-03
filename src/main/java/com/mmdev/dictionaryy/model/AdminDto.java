package com.mmdev.dictionaryy.model;

import lombok.Builder;

import java.util.List;

@Builder
public record AdminDto(
		String name,
		List<Long> schoolIds
) {
}
