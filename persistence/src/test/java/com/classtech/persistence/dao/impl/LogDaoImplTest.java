package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.LogDao;

public class LogDaoImplTest extends AbstractDaoTest {
	@Autowired
	LogDao logDao;

	@Test
	public void testFindBySchoolClass() {
		logDao.findBySchoolClass("上海小学", "2009", "一班");
	}

	@Test
	public void testFindByStudent() {
		logDao.findByStudent("上海小学", "2009", "一班", (short) 1);
	}

	@Test
	public void testFindByTeacher() {
		logDao.findByTeacher("上海小学", (short) 0);
	}
}
