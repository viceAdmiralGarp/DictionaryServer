package com.mmdev.dictionaryy.model.school;

import jakarta.validation.constraints.NotNull;

public record UpdateSchoolByAdminDto(
		@NotNull
		Long adminId
) {
}
