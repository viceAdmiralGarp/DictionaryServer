package com.mmdev.dictionaryy.repository.school;

import com.mmdev.dictionaryy.entity.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	Optional<School> findByAdminId(Long adminId);

	Boolean existsSchoolByName(String name);

	Boolean existsByAdminId(Long adminId);
}
