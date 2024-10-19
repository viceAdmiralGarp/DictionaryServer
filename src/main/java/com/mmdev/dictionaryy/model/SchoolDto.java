package com.mmdev.dictionaryy.model;

import java.util.List;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;

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

	public School toSchool(Admin admin , List<Topic> topics) {
		return School.builder()
				.name(this.name())
				.admin(admin)
				.topics(topics)
				.build();
	}
}
