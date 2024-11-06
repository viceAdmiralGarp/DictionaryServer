package com.mmdev.dictionaryy.model.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdminNameDto(
		@NotNull
		@Size(max = 64)
		String name
) {
}
