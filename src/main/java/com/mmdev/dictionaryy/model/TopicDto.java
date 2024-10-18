package com.mmdev.dictionaryy.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TopicDto(

		Long id,
		@NotNull
		String name,
		@NotNull
		Long schoolId
) {
}
