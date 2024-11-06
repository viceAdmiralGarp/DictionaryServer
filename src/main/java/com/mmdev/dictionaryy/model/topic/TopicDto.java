package com.mmdev.dictionaryy.model.topic;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TopicDto(

		Long id,

		@NotNull
		@Size(max=256)
		String name,

		@NotNull
		Long schoolId
) {
	public Topic toTopic(School school){
		return Topic.builder()
				.name(name)
				.school(school)
				.build();
	}
}
