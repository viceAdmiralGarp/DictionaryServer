package com.mmdev.dictionaryy.service;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

	private SchoolRepository schoolRepository;

	public List<School> getAllSchools() {
		return schoolRepository.findAll();
	}

	public School getSchoolById(Long id) {
		return schoolRepository.findById(id).orElse(null);
	}

	public School createSchool(School school) {
		return schoolRepository.save(school);
	}

	public School updateSchool(Long id, School school) {
		school.setId(id);
		return schoolRepository.save(school);
	}

	public void deleteSchool(Long id) {
		schoolRepository.deleteById(id);
	}
}
