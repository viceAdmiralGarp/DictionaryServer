package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.admin.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;

	public List<Admin> getAllAdmins() {
		return adminRepository.findAll()
				.stream()
				.toList();
	}

	public Admin findAdminById(Long id) {
		return adminRepository.findById(id).
				orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}

	@Transactional
	public AdminDto createAdmin(AdminDto adminDto) {
		Admin admin = adminDto.toAdmin();
		validateAdminByEmail(adminDto.email());
		Admin savedAdmin = adminRepository.save(admin);
		return savedAdmin.toDto();
	}

	@Transactional
	public void deleteAdminById(Long id) {
		Admin admin = findAdminById(id);
		adminRepository.delete(admin);
	}

	@Transactional
	public void updateAdminNameById(Long id, String name) {
		Admin admin = findAdminById(id);
		admin.setName(name);
	}

	@Transactional
	public void updateAdminEmailById(Long id, String email) {
		Admin admin = findAdminById(id);
		validateAdminByEmail(email);
		admin.setEmail(email);
	}

	@Transactional
	public void updateAdminPasswordById(Long id, String password) {
		Admin admin = findAdminById(id);
		admin.setPassword(password);
	}

	public void validateAdminByEmail(String email) {
		if (adminRepository.existsByEmail(email)) {
			throw new EntityAlreadyRelatedException("Email:" + email + " is already in use by another admin.");
		}
	}
}
