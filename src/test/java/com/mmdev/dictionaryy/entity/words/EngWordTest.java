package com.mmdev.dictionaryy.entity.words;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.student.Student;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EngWordTest {

	private EngWord engWord;
	private static Session session;

	@BeforeAll
	static void createSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void startTransaction(){
		session.beginTransaction();
	}

	@BeforeEach
	void createEngWord() {
		engWord = EngWord.builder()
				.name("certain")
				.build();
	}

	@Test
	void checkManyToMany(){
		School school = session.get(School.class, 1L);
		EngWord engWord1 = session.get(EngWord.class, 1L);
		Student alex = Student.builder()
				.firstName("Alex")
				.lastName("Ivanov")
				.email("alex@gmail.com")
				.password("123")
				.school(school)
				.groupName("123")
				.build();
		alex.addEngWord(engWord1);
		session.save(alex);
	}

	@Test
	void saveWord(){
		session.save(engWord);
	}

	@AfterEach
	void finishTransaction(){
		session.getTransaction().commit();
	}

}
