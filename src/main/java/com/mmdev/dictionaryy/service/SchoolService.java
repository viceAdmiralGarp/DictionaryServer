package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.school.SchoolDtoMapper;
import com.mmdev.dictionaryy.mapper.school.SchoolMapper;
import com.mmdev.dictionaryy.model.SchoolDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final AdminRepository adminRepository;
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
		School school = schoolValidation(id, schoolDto);
		schoolRepository.save(school);
		return schoolDtoMapper.map(school);
	}

	//Do I really need this method if I have a NOTNULL annotation over the fields?
	public SchoolDto patchSchoolById(Long id, Map<String, Object> updates) {
		School schoolById = schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
		SchoolDto schoolDto = schoolDtoMapper.map(schoolById);
		School school = schoolValidation(id, schoolDto);
		updates.forEach((key, value) -> {
			switch (key) {
				case "name":
					school.setName((String) value);
					break;
				case "adminId":
					school.setAdmin((Admin) value);
					break;
			}
		});
		School updatedSchool = schoolRepository.save(school);
		return schoolDtoMapper.map(updatedSchool);
	}

	public void deleteSchool(Long id) {
		schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
		schoolRepository.deleteById(id);
	}

	private School schoolValidation(Long id, SchoolDto schoolDto) {
		School school = schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));

		if (school.getName().equals(schoolDto.name())) {
			throw new EntityAlreadyRelatedException(
					"The School" + schoolDto.name() + " is already in use by another school.");
		}

		school.setName(schoolDto.name());

		if (school.getAdmin() == null || !school.getAdmin().getId().equals(schoolDto.adminId())) {
			Admin admin = adminRepository.findById(schoolDto.adminId())
					.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + schoolDto.adminId()));

			Optional<School> optionalSchoolByAdminId = schoolRepository.findByAdminId(schoolDto.adminId());

			if (optionalSchoolByAdminId.isPresent() && !optionalSchoolByAdminId.get().getId().equals(id)) {
				throw new EntityAlreadyRelatedException(
						"The administrator with this ID " + schoolDto.adminId() + " belongs to another school.");
			}
			school.setAdmin(admin);
		}
		return school;
	}
}
