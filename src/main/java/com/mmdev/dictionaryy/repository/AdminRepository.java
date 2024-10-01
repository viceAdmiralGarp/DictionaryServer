package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.admins.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {}
