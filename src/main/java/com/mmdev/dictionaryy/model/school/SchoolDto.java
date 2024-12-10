package com.mmdev.dictionaryy.model.school;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;

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

	public School toSchool(Admin admin) {
		return School.builder()
				.name(name)
				.admin(admin)
				.build();
	}
}
