package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.service.AdminService;
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
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@GetMapping
	public List<AdminDto> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
		AdminDto admin = adminService.getAdminById(id)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
		return ResponseEntity.ok(admin);
	}

	@PostMapping
	public AdminDto createAdmin(@RequestBody @Validated AdminDto admin) {
		return adminService.createAdmin(admin);
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
		return ResponseEntity.noContent().build();
	}
}
