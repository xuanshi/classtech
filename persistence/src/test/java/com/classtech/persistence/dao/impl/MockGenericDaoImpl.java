package com.classtech.persistence.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
class MockGenericDaoImpl extends GenericDaoImpl<Object> {

	public void printVersion() {
		Session session = getSession();
		Transaction t = session.beginTransaction();
		List<?> objs = session.createSQLQuery("SELECT version()").list();
		System.out.println(objs.get(0));
		t.commit();
	}
}
