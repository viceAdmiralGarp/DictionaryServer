package com.mmdev.dictionaryy.mapper.topic;

import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.TopicDto;
import org.springframework.stereotype.Component;

@Component
public class TopicDtoMapper implements Mapper<Topic, TopicDto> {
	@Override
	public TopicDto map(Topic object) {
		return TopicDto.builder()
				.id(object.getId())
				.name(object.getName())
				.schoolId(object.getSchool().getId())
				.build();
	}
}
