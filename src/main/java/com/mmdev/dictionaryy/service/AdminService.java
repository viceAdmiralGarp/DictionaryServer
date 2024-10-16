package com.mmdev.dictionaryy.service;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.exception.EntityNotFoundException;
import com.mmdev.dictionaryy.mapper.admin.AdminDtoMapper;
import com.mmdev.dictionaryy.mapper.admin.AdminMapper;
import com.mmdev.dictionaryy.model.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

	public AdminDto getAdminById(Long id) {
		return adminRepository.findById(id)
				.map(adminDtoMapper::map)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
	}

	public AdminDto createAdmin(AdminDto adminDto) {
		Admin admin =  adminMapper.map(adminDto);
		Admin savedAdmin = adminRepository.save(admin);
		return adminDtoMapper.map(savedAdmin);
	}

	public AdminDto updateAdminById(Long id, AdminDto adminDto) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

		if (!admin.getEmail().equals(adminDto.email()) && adminRepository.existsByEmail(adminDto.email())) {
			throw new EntityExistsException("Email is already in use by another admin.");
		}

		admin.setName(adminDto.name());
		admin.setEmail(adminDto.email());
		admin.setPassword(adminDto.password());

		Admin savedAdmin = adminRepository.save(admin);
		return adminDtoMapper.map(savedAdmin);
	}

	public void deleteAdminById(Long id) {
		if(adminRepository.findById(id).isEmpty()){
			throw new EntityNotFoundException("Admin not found with id: " + id);
		}
		adminRepository.deleteById(id);
	}

	public AdminDto patchAdminById(Long id, Map<String, Object> updates) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

		updates.forEach((key, value) -> {
			switch (key) {
				case "name":
					admin.setName((String) value);
					break;
				case "email":
					admin.setEmail((String) value);
					break;
				case "password":
					admin.setPassword((String) value);
					break;
			}
		});

		Admin savedAdmin = adminRepository.save(admin);
		return adminDtoMapper.map(savedAdmin);
	}
}
