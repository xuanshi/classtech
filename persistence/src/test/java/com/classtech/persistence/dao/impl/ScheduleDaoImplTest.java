package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.ScheduleDao;

public class ScheduleDaoImplTest extends AbstractDaoTest {

	@Autowired
	ScheduleDao dao;

	@Test
	public void testFindByName() {
		dao.findByName("上海小学", "2009", "一班");
	}

	@Test
	public void testFindByTeacher() {
		dao.findByTeacher("上海小学", (short) 0);
	}
}
