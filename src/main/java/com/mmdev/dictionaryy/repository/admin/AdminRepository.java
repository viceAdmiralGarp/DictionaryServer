package com.mmdev.dictionaryy.repository.admin;

import com.mmdev.dictionaryy.entity.admins.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Boolean existsByEmail(String email);
}
