package com.mmdev.dictionaryy.entity.school;

import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SchoolTest {

	private School school;
	private Session session;

	@BeforeEach
	void startSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void createSchool() {
		school = School.builder()
				.name("grand")
				.build();
	}

	@BeforeEach
	void startTransaction() {
		session.beginTransaction();
	}

	@Test
	void saveAdmin() {
		session.save(school);
	}

	@Test
	void getSchoolById() {
		School school1 = session.get(School.class, 1L);
		System.out.println(school1.toString());
		Assertions.assertNotNull(school1.toString());
	}

	@AfterEach
	void finishTransaction() {
		session.getTransaction().commit();
	}
}
