package com.mmdev.dictionaryy.service;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.school.SchoolDtoMapper;
import com.mmdev.dictionaryy.mapper.school.SchoolMapper;
import com.mmdev.dictionaryy.model.SchoolDto;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final SchoolDtoMapper schoolDtoMapper;
	private final SchoolMapper schoolMapper;

	public List<SchoolDto> getAllSchools() {
		return schoolRepository.findAll().stream()
				.map(schoolDtoMapper::map)
				.collect(Collectors.toList());
	}

	public SchoolDto getSchoolById(Long id) {
		return schoolRepository.findById(id)
				.map(schoolDtoMapper::map)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
	}

	public SchoolDto createSchool(SchoolDto schoolDto) {
		School school = schoolMapper.map(schoolDto);
		School savedSchool = schoolRepository.save(school);
		return schoolDtoMapper.map(savedSchool);
	}

	public SchoolDto updateSchool(Long id, SchoolDto schoolDto) {
		School school = schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));


		return null;
	}

	public void deleteSchool(Long id) {
		schoolRepository.deleteById(id);
	}

}
