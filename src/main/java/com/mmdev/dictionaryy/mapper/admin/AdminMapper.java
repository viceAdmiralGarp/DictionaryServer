package com.mmdev.dictionaryy.mapper.admin;

import com.mmdev.dictionaryy.entity.admins.Admin;
import com.mmdev.dictionaryy.mapper.Mapper;
import com.mmdev.dictionaryy.model.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements Mapper<AdminDto, Admin> {
	@Override
	public Admin map(AdminDto object) {
		return Admin.builder()
				.name(object.name())
				.email(object.email())
				.password(object.password())
				.build();
	}
}
