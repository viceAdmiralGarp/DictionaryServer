package com.mmdev.dictionaryy.model.topics.subtopic;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import com.mmdev.dictionaryy.entity.topics.Topic;
import lombok.Builder;

@Builder
public record SubTopicDto(
		Long id,
		String name,
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
