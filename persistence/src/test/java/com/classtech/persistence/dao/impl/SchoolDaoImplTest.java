package com.classtech.persistence.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.model.School;
import com.classtech.persistence.dao.SchoolDao;

public class SchoolDaoImplTest extends AbstractDaoTest {

	@Autowired
	SchoolDao schoolDao;

	@Test
	public void test() {
		List<School> schools = schoolDao.findAll();
		System.out.println(schools.size());
		System.out.println(schools.get(0).getName());
	}
}
