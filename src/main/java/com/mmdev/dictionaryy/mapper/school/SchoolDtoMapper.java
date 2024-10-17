package com.mmdev.dictionaryy.mapper.school;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.SchoolDto;
import org.springframework.stereotype.Component;

@Component
public class SchoolDtoMapper implements Mapper<School, SchoolDto> {
	@Override
	public SchoolDto map(School object) {
		return SchoolDto.builder()
				.id(object.getId())
				.name(object.getName())
				.adminId(object.getAdmin().getId())
				.build();
	}
}
