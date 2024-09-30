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

public class NativeWordTest {

	private NativeWord nativeWord;
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
		nativeWord = NativeWord.builder()
				.name("уверен")
				.build();
	}

	@Test
	void checkManyToMany(){
		School school = session.get(School.class, 1L);
		NativeWord nativeWord1 = session.get(NativeWord.class, 1L);
		Student alex = Student.builder()
				.firstName("Alex")
				.lastName("Ivanov")
				.email("alex1@gmail.com")
				.password("123")
				.school(school)
				.groupName("123")
				.build();
		alex.addNativeWord(nativeWord1);
		session.save(alex);
	}

	@Test
	void saveWord(){
		session.save(nativeWord);
	}

	@AfterEach
	void finishTransaction(){
		session.getTransaction().commit();
	}
}
