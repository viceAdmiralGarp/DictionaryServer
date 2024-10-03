package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

	private SchoolService schoolService;

	@GetMapping
	public List<School> getAllSchools() {
		return schoolService.getAllSchools();
	}

	@GetMapping("/{id}")
	public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
		School school = schoolService.getSchoolById(id);
		if (school == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(school);
	}

	@PostMapping
	public School createSchool(@RequestBody School school) {
		return schoolService.createSchool(school);
	}

	@PutMapping("/{id}")
	public ResponseEntity<School> updateSchool(
			@PathVariable Long id,
			@RequestBody School school) {
		School updatedSchool = schoolService.updateSchool(id, school);
		return ResponseEntity.ok(updatedSchool);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
		schoolService.deleteSchool(id);
		return ResponseEntity.noContent().build();
	}
}
