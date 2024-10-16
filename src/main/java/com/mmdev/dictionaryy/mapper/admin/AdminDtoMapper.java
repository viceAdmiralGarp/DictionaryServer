package com.mmdev.dictionaryy.mapper.admin;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminDtoMapper implements Mapper<Admin, AdminDto> {
	@Override
	public AdminDto map(Admin object) {
		return AdminDto.builder()
				.name(object.getName())
				.email(object.getEmail())
				.password(object.getPassword())
				.build();
	}
}
