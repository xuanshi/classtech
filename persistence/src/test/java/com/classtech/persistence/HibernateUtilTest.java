package com.classtech.persistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class HibernateUtilTest {

	private SessionFactory factory;

	private String testSql = "SELECT version()";

	@Before
	public void setup() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method m = HibernateUtil.class.getDeclaredMethod("buildSessionFactory",
				String.class);
		m.setAccessible(true);
		Object o = m.invoke(null, "/hibernate.cfg.test.xml");
		factory = (SessionFactory) o;
	}

	@Test
	public void testSql() {
		Session session = factory.openSession();
		List<?> objs = session.createSQLQuery(testSql).list();
		System.out.println(objs.size());
		System.out.println(objs.get(0));
		session.close();
	}
}
