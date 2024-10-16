package com.mmdev.dictionaryy.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record AdminDto(
		@NotNull
		@Size(max = 64)
		String name,

		@Email
		@Size(max = 128)
		String email,

		@NotNull
		@Size(max = 128)
		String password
) {}
