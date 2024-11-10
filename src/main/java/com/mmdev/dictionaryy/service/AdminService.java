package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.exception.EntityAlreadyRelatedException;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.model.admin.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;

	public List<AdminDto> getAllAdmins() {
		return adminRepository.findAll()
				.stream()
				.map(Admin::toDto)
				.toList();
	}

	public AdminDto getAdminById(Long id) {
		return adminRepository.findById(id)
				.map(Admin::toDto)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}

	public AdminDto createAdmin(AdminDto adminDto) {
		Admin admin = adminDto.toAdmin();
		adminExistsByEmail(adminDto.email());//TODO rename to more meaningful, e. g. validate
		Admin savedAdmin = adminRepository.save(admin);
		return savedAdmin.toDto();
	}

	public void updateAdminByName(Long id, String name) {//TODO rename updateAdminNameById
		Admin admin = findAdminById(id);
		admin.setName(name);
		adminRepository.save(admin);
	}

	public void updateAdminByEmail(Long id, String email) {
		Admin admin = findAdminById(id);
		adminExistsByEmail(email);
		admin.setEmail(email);
		adminRepository.save(admin);
	}

	public void updateAdminByPassword(Long id, String password) {
		Admin admin = findAdminById(id);
		admin.setPassword(password);
		adminRepository.save(admin);
	}

	public void deleteAdminById(Long id) {
		Admin admin = findAdminById(id);
		adminRepository.deleteById(admin.getId());
	}

	private Admin findAdminById(Long id) {
		return adminRepository.findById(id).
				orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}

	private void adminExistsByEmail(String email) {
		if (adminRepository.existsByEmail(email)) {
			throw new EntityAlreadyRelatedException("Email is already in use by another admin.");
		}
	}
}
