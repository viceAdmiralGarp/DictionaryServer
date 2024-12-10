package com.mmdev.dictionaryy.service.school;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.school.SchoolDto;
import com.mmdev.dictionaryy.repository.school.SchoolRepository;
import com.mmdev.dictionaryy.service.admin.AdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final AdminService adminService;

	public List<School> getAllSchools() {
		return schoolRepository.findAll()
				.stream()
				.toList();
	}

	public School findSchoolById(Long id) {
		return schoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
	}

	@Transactional
	public void createSchool(SchoolDto schoolDto) {
		Admin admin = adminService.findAdminById(schoolDto.adminId());
		schoolExistsByAdminId(schoolDto);
		School school = schoolDto.toSchool(admin);
		schoolRepository.save(school);
	}

	@Transactional
	public void updateSchoolNameById(Long id, String name) {
		School school = findSchoolById(id);
		validateSchoolName(name);
		school.setName(name);
		schoolRepository.save(school);
	}

	@Transactional
	public void updateSchoolAdminById(Long schoolId, Long adminId) {
		School school = findSchoolById(schoolId);
		Admin admin = adminService.findAdminById(adminId);
		validateAdminDoesNotBelongToAnotherSchool(adminId, schoolId);
		school.setAdmin(admin);
	}

	@Transactional
	public void deleteSchoolById(Long id) {
		School schoolById = findSchoolById(id);
		schoolRepository.delete(schoolById);
	}

	public void validateAdminDoesNotBelongToAnotherSchool(Long adminId, Long schoolId) {//Get the admin by ID and check the school of this admin for equality with the schoolId argument.
		Optional<School> optionalSchoolByAdminId = schoolRepository.findByAdminId(adminId);

		if (optionalSchoolByAdminId.isPresent() && !optionalSchoolByAdminId.get().getId().equals(schoolId)) {
			throw new EntityAlreadyRelatedException(
					"The administrator with this ID " + adminId + " belongs to another school.");
		}
	}

	public void schoolExistsByAdminId(SchoolDto schoolDto) {
		if (schoolRepository.existsByAdminId(schoolDto.adminId())) {
			throw new EntityAlreadyRelatedException(
					"The administrator with this ID " + schoolDto.adminId() + " belongs to another school.");
		}
	}

	public void validateSchoolName(String name) {
		if (schoolRepository.existsSchoolByName(name)) {
			throw new EntityAlreadyRelatedException(
					"The School with this name " + name + " belongs to another school.");
		}
	}
}
