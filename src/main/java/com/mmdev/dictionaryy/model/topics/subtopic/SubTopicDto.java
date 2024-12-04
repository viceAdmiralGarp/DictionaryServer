package com.mmdev.dictionaryy.model.topics.subtopic;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import com.mmdev.dictionaryy.entity.topics.Topic;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SubTopicDto(
		Long id,

		@NotNull
		@Size(max=256)
		String name,

		@NotNull
		Long topicId
) {

	public SubTopic toSubTopic(Topic topic){
		return SubTopic.builder()
				.id(id)
				.name(name)
				.topic(topic)
				.build();
	}
}
