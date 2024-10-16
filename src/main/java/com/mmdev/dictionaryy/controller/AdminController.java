package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController{

	private final AdminService adminService;

	@GetMapping
	public List<AdminDto> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
		AdminDto admin = adminService.getAdminById(id);
		return ResponseEntity.ok(admin);
	}

	@PostMapping
	public ResponseEntity<AdminDto> createAdmin(@RequestBody @Validated AdminDto adminDto) {
		AdminDto admin = adminService.createAdmin(adminDto);
		return ResponseEntity.ok(admin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminDto> updateAdmin(
			@PathVariable Long id,
			@RequestBody @Validated AdminDto adminDto) {
		AdminDto updatedAdmin = adminService.updateAdminById(id, adminDto);
		return ResponseEntity.ok(updatedAdmin);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdminById(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AdminDto> patchAdmin(
			@PathVariable Long id,
			@RequestBody Map<String, Object> updates) {
		AdminDto updatedAdmin = adminService.patchAdminById(id, updates);
		return ResponseEntity.ok(updatedAdmin);
	}

}
