package com.mmdev.dictionaryy.model.topics.subtopic;

import lombok.Builder;

@Builder
public record UpdateSubTopicNameDto(
		String name
) {
}
