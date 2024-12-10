package com.mmdev.dictionaryy.controller.school;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.model.school.SchoolDto;
import com.mmdev.dictionaryy.service.SchoolService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

	private final SchoolService schoolService;

	@GetMapping
	public ResponseEntity<List<SchoolDto>> getAllSchools() {
		List<SchoolDto> allSchools = schoolService.getAllSchools()
				.stream()
				.map(School::toDto)
				.toList();
		return ResponseEntity.ok(allSchools);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SchoolDto> findSchoolById(@PathVariable Long id) {
		SchoolDto school = schoolService.findSchoolById(id).toDto();
		return ResponseEntity.ok(school);
	}

	@PostMapping
	public void createSchool(@RequestBody @Validated SchoolDto schoolDto) {
		schoolService.createSchool(schoolDto);
	}

	@PutMapping("/{id}/name")
	public void updateSchoolNameById(@PathVariable Long id, @RequestBody @NotNull String schoolAdminName) {
		schoolService.updateSchoolNameById(id, schoolAdminName);

	}

	@PutMapping("/{id}/admin")
	public void updateSchoolAdminById(@PathVariable Long id, @RequestBody @NotNull Long adminId) {
		schoolService.updateSchoolAdminById(id, adminId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
		schoolService.deleteSchoolById(id);
		return ResponseEntity.ok().build();
	}
}
