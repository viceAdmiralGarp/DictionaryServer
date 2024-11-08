package com.mmdev.dictionaryy.model.topics.topic;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTopicNameDto(
		@NotNull
		@Size(max=256)
		String name
) {
}
