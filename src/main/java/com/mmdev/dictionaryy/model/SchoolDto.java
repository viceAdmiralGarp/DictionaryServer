package com.mmdev.dictionaryy.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SchoolDto(
		Long id,

		@NotNull
		@Size(max = 256)
		String name,

		@NotNull
		Long adminId
) {
}
