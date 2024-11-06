package com.mmdev.dictionaryy.model.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicSchoolByIdDto(
		@NotNull
		Long schoolId
) {
}
