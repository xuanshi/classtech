package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.TeacherDao;

public class TeacherDaoImplTest extends AbstractDaoTest {

	@Autowired
	private TeacherDao teacherDao;

	@Test
	public void testFindBySchool() {
		teacherDao.findBySchool("上海小学");
	}

	@Test
	public void testFindByTeacherNo() {
		teacherDao.findByTeacherNo("上海小学", (short) 1);
	}
}
