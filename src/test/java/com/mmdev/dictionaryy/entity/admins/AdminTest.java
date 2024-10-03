package com.mmdev.dictionaryy.entity.admins;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.repository.AdminRepository;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AdminTest {

	private Admin admin;
	private School school;
	private Session session;
	private Topic topic;
	private AdminRepository adminRepository;

	@BeforeEach
	void startSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void createAdminAndSchools() {
		topic = Topic.builder()
				.name("coffee")
				.build();

		school = School.builder()
				.name("Grand")
				.topics(new ArrayList<>(Collections.singletonList(topic)))
				.build();

		admin = Admin.builder()
				.name("Alex")
				.email("Alex@gmail.com")
				.password("123")
				.school(school)
				.build();
	}

	@BeforeEach
	void startTransaction() {
		session.beginTransaction();
	}

	@Test
	void saveAdmin() {
		School grand = School.builder()
				.name("Grand")
				.topics(new ArrayList<>(Collections.singletonList(topic)))
				.build();

		Admin alex = Admin.builder()
				.name("Alex")
				.email("Alex@gmail.com")
				.password("123")
				.school(grand)
				.build();

		grand.setAdmin(alex);
		session.persist(grand);
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
