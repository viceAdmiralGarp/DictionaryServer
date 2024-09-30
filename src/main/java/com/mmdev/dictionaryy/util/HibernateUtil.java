package com.mmdev.dictionaryy.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class was created only to accelerate DB testing because I too
 * lazy to create Repository on each class and test all the entities =)
 * Also I wanted to remember how to work with clear hibernate
 */

public  class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		return configuration.buildSessionFactory();
	}

}

