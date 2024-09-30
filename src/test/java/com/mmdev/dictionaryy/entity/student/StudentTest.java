package com.mmdev.dictionaryy.entity.student;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

	private Student student;
	private Session session;

	@BeforeEach
	void createSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void createSubTopic() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		School school = session.get(School.class, 1L);
		student = Student.builder()
				.firstName("Matvey")
				.lastName("Marchenko")
				.email("mat@gmail.com")
				.password("123")
				.groupName("Sun 11:00")
				.school(school)
				.build();
		session.getTransaction().commit();
	}

	@BeforeEach
	void startTransaction() {
		session.beginTransaction();
	}


	@Test
	void saveTopic() {
		session.save(student);
	}

	@AfterEach
	void finishTransaction() {
		session.getTransaction().commit();
	}
}
