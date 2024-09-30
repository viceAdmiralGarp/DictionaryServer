package com.mmdev.dictionaryy.entity.topics;

import com.mmdev.dictionaryy.entity.words.EngWord;
import com.mmdev.dictionaryy.entity.words.NativeWord;
import com.mmdev.dictionaryy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubTopicTest {

	private SubTopic subTopic;
	private Session session;

	@BeforeEach
	void createSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@BeforeEach
	void startTransaction() {
		session.beginTransaction();
	}

	@Test
	void saveSubTopic(){
		session.save(subTopic);
	}

	@Test
	void checkManyToMany(){
		SubTopic subTopic1 = session.get(SubTopic.class, 1L);
		EngWord word = EngWord.builder()
				.name("cofee")
				.build();
		subTopic1.addEngWord(word);
		session.save(word);
	}

	@AfterEach
	void finishTransaction() {
		session.getTransaction().commit();
	}
}
