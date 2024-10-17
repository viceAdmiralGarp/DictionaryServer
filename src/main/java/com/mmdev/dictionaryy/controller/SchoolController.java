package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.SchoolDto;
import com.mmdev.dictionaryy.service.SchoolService;
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
	public List<SchoolDto> getAllSchools() {
		return schoolService.getAllSchools();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SchoolDto> getSchoolById(@PathVariable Long id) {
		SchoolDto school = schoolService.getSchoolById(id);
		return ResponseEntity.ok(school);
	}

	@PostMapping
	public ResponseEntity<SchoolDto> createSchool(@RequestBody @Validated SchoolDto schoolDto) {
		SchoolDto school = schoolService.createSchool(schoolDto);
		return ResponseEntity.ok(school);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SchoolDto> updateSchool(
			@PathVariable Long id,
			@RequestBody @Validated SchoolDto school) {
		SchoolDto updatedSchool = schoolService.updateSchool(id, school);
		return ResponseEntity.ok(updatedSchool);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
		schoolService.deleteSchool(id);
		return ResponseEntity.ok().build();
	}
}
