package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.admin.AdminDtoMapper;
import com.mmdev.dictionaryy.mapper.admin.AdminMapper;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;
	private final AdminDtoMapper adminDtoMapper;
	private final AdminMapper adminMapper;

	public List<AdminDto> getAllAdmins() {
		return adminRepository.findAll()
				.stream()
				.map(adminDtoMapper::map)
				.collect(Collectors.toList());
	}

	public Optional<AdminDto> getAdminById(Long id) {
		return adminRepository.findById(id)
				.map(adminDtoMapper::map);
	}

	public AdminDto createAdmin(AdminDto adminDto) {
		Admin admin =  adminMapper.map(adminDto);
		Admin savedAdmin = adminRepository.save(admin);
		return adminDtoMapper.map(savedAdmin);
	}

	public AdminDto updateAdminById(Long id, AdminDto adminDto) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

		admin.setName(adminDto.name());
		admin.setEmail(adminDto.email());
		admin.setPassword(adminDto.password());

		adminRepository.save(admin);
		return adminDtoMapper.map(admin);
	}

	public void deleteAdminById(Long id) {
		adminRepository.deleteById(id);
	}
}
