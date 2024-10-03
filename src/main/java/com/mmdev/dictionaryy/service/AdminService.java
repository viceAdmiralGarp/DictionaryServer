package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

	private AdminRepository adminRepository;

	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	public AdminDto getAdminById(Integer id) {
		return adminRepository.findById(id)
				.map(admin -> AdminDto.builder()
						.name(admin.getName())
						.build())
				.orElse(null);
	}

	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Admin updateAdmin(Integer id, Admin admin) {
		admin.setId(id);
		return adminRepository.save(admin);
	}

	public void deleteAdmin(Integer id) {
		adminRepository.deleteById(id);
	}
}
