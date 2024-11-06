package com.mmdev.dictionaryy.model.school;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSchoolNameDto(
		@NotNull
		@Size(max = 256)
		String name
) {
}
