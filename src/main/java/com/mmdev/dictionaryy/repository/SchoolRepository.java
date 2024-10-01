package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {}
