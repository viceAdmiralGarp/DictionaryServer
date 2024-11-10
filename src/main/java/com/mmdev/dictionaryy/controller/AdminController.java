package com.mmdev.dictionaryy.controller;

import com.mmdev.dictionaryy.model.admin.AdminDto;
import com.mmdev.dictionaryy.model.admin.UpdateAdminEmailDto;
import com.mmdev.dictionaryy.model.admin.UpdateAdminNameDto;
import com.mmdev.dictionaryy.model.admin.UpdateAdminPasswordDto;
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
	public ResponseEntity<List<AdminDto>> getAllAdmins() {
		List<AdminDto> allAdmins = adminService.getAllAdmins();
		return ResponseEntity.ok(allAdmins);
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

	@PutMapping("/{id}/name")
	public void updateAdminByName(
			@PathVariable Long id,
			@RequestBody @Validated UpdateAdminNameDto adminDto) {
		adminService.updateAdminByName(id, adminDto.name());
	}


	@PutMapping("/{id}/email")
	public void updateAdminByEmail(
			@PathVariable Long id,
			@RequestBody @Validated UpdateAdminEmailDto adminDto) {//TODO Use string instead of object
		adminService.updateAdminByEmail(id, adminDto.email());
	}

	@PutMapping("/{id}/password")
	public void updateAdminByPassword(
			@PathVariable Long id,
			@RequestBody @Validated UpdateAdminPasswordDto adminDto) {
		adminService.updateAdminByPassword(id, adminDto.password());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdminById(id);
		return ResponseEntity.ok().build();
	}


}
