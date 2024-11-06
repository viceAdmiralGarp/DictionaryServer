package com.mmdev.dictionaryy.model.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdminEmailDto(
		@NotNull
		@Size(max = 128)
		String email) {
}
