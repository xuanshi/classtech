package com.classtech.persistence.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.model.Person;
import com.classtech.model.School;
import com.classtech.model.Teacher;
import com.classtech.persistence.dao.PersonDao;
import com.classtech.persistence.dao.SchoolDao;
import com.classtech.persistence.dao.TeacherDao;

public class PersonDaoImplTest extends AbstractDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	SchoolDao schoolDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private TeacherDao teacherDao;

	@Before
	public void setup() {
		clear();
	}

	private void clear() {
		StatelessSession session = sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("DELETE FROM School");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Teacher");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Person");
		q.executeUpdate();
		tx.commit();
		session.close();
	}

	@Test
	public void testInsert() {
		School school = new School();
		school.setName("Test");
		schoolDao.save(school);
		Person person = new Person();
		person.setFirstName("First");
		person.setLastName("Last");
		person.setEmail("a@b");
		person.setMobile("123456789");
		personDao.save(person);
		Teacher teacher = new Teacher();
		teacher.setPerson(person);
		teacher.setSchool(school);
		teacher.setTeacherNumber((short) 123);
		teacherDao.save(teacher);
	}
}
