package com.mmdev.dictionaryy.model.admin;

import com.mmdev.dictionaryy.entity.admins.Admin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record AdminDto(
		Long id,

		@NotNull
		@Size(max = 64)
		String name,

		@NotNull
		@Size(max = 128)
		String email,

		@NotNull
		@Size(max = 128)
		String password
) {

	public Admin toAdmin(){
		return Admin.builder()
				.name(name)
				.email(email)
				.password(password)
				.build();
	}
}
