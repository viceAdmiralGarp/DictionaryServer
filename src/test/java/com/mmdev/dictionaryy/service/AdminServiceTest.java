package com.mmdev.dictionaryy.service;


import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.model.admin.AdminDto;
import com.mmdev.dictionaryy.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

	private static final Long ADMIN_ID = 1L;
	private Admin admin;
	private AdminDto adminDto;

	@Mock
	private AdminRepository adminRepository;
	@InjectMocks
	private AdminService adminService;

	@BeforeEach
	void createAdminAndAdminDto() {
		admin = Admin.builder()
				.id(ADMIN_ID)
				.name("Alex")
				.email("Alex@gmail.com")
				.password("123")
				.build();

		adminDto = AdminDto.builder()
				.name("Alex")
				.email("Alex@gmail.com")
				.password("123")
				.build();

	}

	@Test
	void getAllAdmins() {
		List<Admin> admins = Collections.singletonList(admin);
		List<AdminDto> adminDtos = Collections.singletonList(adminDto);

		doReturn(admins).when(adminRepository).findAll();

		List<AdminDto> actualResult = adminService.getAllAdmins();

		assertEquals(1, actualResult.size());
		assertEquals(adminDtos, actualResult);

		verify(adminRepository).findAll();

	}

	@Test
	void getAdminById() {

	}

	@Test
	void createAdmin() {
	}

	@Test
	void updateAdminById() {
	}

	@Test
	void deleteAdminById() {
	}
}