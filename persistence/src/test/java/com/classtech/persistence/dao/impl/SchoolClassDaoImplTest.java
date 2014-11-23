package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.SchoolClassDao;

public class SchoolClassDaoImplTest extends AbstractDaoTest {
	@Autowired
	SchoolClassDao schoolClassDao;

	@Test
	public void test() {
		schoolClassDao.findByName("上海小学", "2009", "一班");
	}
}
