package com.mmdev.dictionaryy.entity.admins;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AdminTest {

	private Admin admin;
	private School school;
	private Session session;

	@BeforeEach
	void startSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void createAdminAndSchools() {
		school = School.builder()
				.name("Grand")
				.build();

		admin = Admin.builder()
				.id(1)
				.name("Alex")
				.schools(new ArrayList<>())
				.build();
	}

	@BeforeEach
	void startTransaction() {
		session.beginTransaction();
	}

	@Test
	void saveAdmin() {
		session.save(admin);
	}

	@Test
	void getAdminById() {
		Admin adminById = session.get(Admin.class, 1);
		System.out.println(adminById);


		Assertions.assertEquals(admin, adminById);
	}

	@AfterEach
	void finishTransaction() {
		session.getTransaction().commit();
	}

}
