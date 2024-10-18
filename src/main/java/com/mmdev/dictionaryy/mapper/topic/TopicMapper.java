package com.mmdev.dictionaryy.mapper.topic;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.TopicDto;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicMapper implements Mapper<TopicDto, Topic> {

	private final SchoolRepository schoolRepository;

	@Override
	public Topic map(TopicDto object) {
		School school = schoolRepository.findById(object.schoolId())
				.orElseThrow(() -> new EntityNotFoundException("School not found by ID: " + object.schoolId()));

		return Topic.builder()
				.name(object.name())
				.school(school)
				.build();
	}
}
