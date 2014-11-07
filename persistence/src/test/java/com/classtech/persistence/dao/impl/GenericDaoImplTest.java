package com.classtech.persistence.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDaoImplTest extends AbstractDaoTest {

	@Autowired
	MockGenericDaoImpl dao;

	@Test
	public void test() {
		dao.printVersion();
	}
}
