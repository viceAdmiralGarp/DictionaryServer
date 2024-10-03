package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {}
