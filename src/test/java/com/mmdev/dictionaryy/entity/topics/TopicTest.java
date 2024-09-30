package com.mmdev.dictionaryy.entity.topics;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopicTest {

	private Topic topic;
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
		topic = Topic.builder()
				.name("coffee")
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
		session.save(topic);
	}

	@AfterEach
	void finishTransaction() {
		session.getTransaction().commit();
	}

}

