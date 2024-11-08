package com.mmdev.dictionaryy.model.topics.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicSchoolByIdDto(
		@NotNull
		Long schoolId
) {
}
