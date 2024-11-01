package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.model.SchoolDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import com.mmdev.dictionaryy.repository.SchoolRepository;
import com.mmdev.dictionaryy.repository.TopicRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final AdminRepository adminRepository;
	private final TopicRepository topicRepository;

	public List<SchoolDto> getAllSchools() {
		return schoolRepository.findAll().stream()
				.map(School::toDto)
				.collect(Collectors.toList());
	}

	public SchoolDto getSchoolById(Long id) {
		return schoolRepository.findById(id)
				.map(School::toDto)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
	}

	public void createSchool(SchoolDto schoolDto) {
		Admin admin = adminRepository.findById(schoolDto.adminId())
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + schoolDto.adminId()));
		List<Topic> topics = topicRepository.findBySchoolId(schoolDto.id());
		schoolRepository.save(schoolDto.toSchool(admin, topics));
	}

	public void updateSchoolNameId(Long id, String name) {
		School school = findSchoolById(id);
		validateSchoolName(name);
		school.setName(name);
		schoolRepository.save(school);
	}

	public void updateSchoolAdminById(Long id, AdminDto adminDto) {
		School school = findSchoolById(id);

		if (needsAdminUpdate(school, adminDto)) {
			Admin admin = findAdminById(adminDto.id());
			validateAdminDoesNotBelongToAnotherSchool(adminDto.id(), id);
			school.setAdmin(admin);
		}
	}

	public void deleteSchool(Long id) {
		schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
		schoolRepository.deleteById(id);
	}

	private Admin findAdminById(Long adminId) {
		return adminRepository.findById(adminId)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
	}

	private void validateAdminDoesNotBelongToAnotherSchool(Long adminId, Long schoolId) {
		Optional<School> optionalSchoolByAdminId = schoolRepository.findByAdminId(adminId);

		if (optionalSchoolByAdminId.isPresent() && !optionalSchoolByAdminId.get().getId().equals(schoolId)) {
			throw new EntityAlreadyRelatedException(
					"The administrator with this ID " + adminId + " belongs to another school.");
		}
	}

	private boolean needsAdminUpdate(School school, AdminDto adminDto) {
		return school.getAdmin() == null || !school.getAdmin().getId().equals(adminDto.id());
	}

	private School findSchoolById(Long id) {
		return schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
	}

	private void validateSchoolName(String name) {
		schoolRepository.findSchoolByName(name)
				.ifPresent(s -> {
					throw new EntityAlreadyRelatedException(
							"The School with this name " + name + " belongs to another school.");
				});
	}
}
