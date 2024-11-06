package com.mmdev.dictionaryy.model.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdminPasswordDto(
		@NotNull
		@Size(max = 128)
		String password
) {
}
