package com.mmdev.dictionaryy.mapper.school;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.SchoolDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import com.mmdev.dictionaryy.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolMapper implements Mapper<SchoolDto, School> {// use lib for mappers

	private final TopicRepository topicRepository;
	private final AdminRepository adminRepository;

	@Override
	public School map(SchoolDto object) {//move to SchoolDto

		Admin admin = adminRepository.findById(object.adminId())
				.orElseThrow(() -> new EntityNotFoundException("Admin not found"));

		return School.builder()
				.name(object.name())
				.admin(admin)
				.topics(topicRepository.findAll())
				.build();
	}
}
