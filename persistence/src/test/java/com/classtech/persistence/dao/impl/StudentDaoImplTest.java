package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.StudentDao;

public class StudentDaoImplTest extends AbstractDaoTest {

	@Autowired
	private StudentDao dao;

	@Test
	public void testFindBySchool() {
		dao.findByStudentNo("上海小学", "2009", "一班", (short) 0);
	}
}
