package com.classtech.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.model.School;
import com.classtech.persistence.dao.SchoolDao;

public class SchoolDaoImplTest extends AbstractDaoTest {

	@Autowired
	SchoolDao schoolDao;

	Long id = null;
	final String name = "上海中学";

	@Before
	public void setup() {
		id = insertData();
	}

	@After
	public void tearDown() {
		deleteData(id);
	}

	@Test
	public void testFindAll() {
		List<School> schools = schoolDao.findAll();
		assertTrue(schools.size() > 0);
	}

	@Test
	public void testFind() {
		School entity = schoolDao.findById(id, true);
		assertNotNull(entity);
		assertEquals(name, entity.getName());
	}

	Long insertData() {
		School entity = new School();
		entity.setName(name);
		entity = schoolDao.save(entity);
		return entity.getId();
	}

	void deleteData(Long id) {
		schoolDao.delete(id);
	}
}
