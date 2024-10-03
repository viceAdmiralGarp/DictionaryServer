package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.service.AdminService;
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
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

	private AdminService adminService;

	@GetMapping
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminDto> getAdminById(@PathVariable Integer id) {
		AdminDto admin = adminService.getAdminById(id);
		if (admin == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(admin);
	}

	@PostMapping
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminService.createAdmin(admin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(
			@PathVariable Integer id,
			@RequestBody Admin admin) {
		Admin updatedAdmin = adminService.updateAdmin(id, admin);
		return ResponseEntity.ok(updatedAdmin);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
		adminService.deleteAdmin(id);
		return ResponseEntity.noContent().build();
	}
}
