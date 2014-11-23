package com.classtech.persistence.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.Log;
import com.classtech.persistence.dao.LogDao;

@Repository
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

	@Override
	public List<Log> findBySchoolClass(String schoolName, String year,
			String schoolClassName) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass()).addOrder(Order.asc("logger"))
				.addOrder(Order.asc("loggee")).addOrder(Order.asc("timestamp"))
				.createCriteria("loggee").createCriteria("schoolClass")
				.add(Restrictions.eq("name", schoolClassName))
				.createCriteria("year")
				.add(Restrictions.eq("entranceYear", year))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findByCriteria(criteria);
	}

	@Override
	public List<Log> findByStudent(String schoolName, String year,
			String schoolClassName, Short studentNo) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass()).addOrder(Order.asc("logger"))
				.addOrder(Order.asc("loggee")).addOrder(Order.asc("timestamp"))
				.createCriteria("loggee")
				.add(Restrictions.eq("studentNumber", studentNo))
				.createCriteria("schoolClass")
				.add(Restrictions.eq("name", schoolClassName))
				.createCriteria("year")
				.add(Restrictions.eq("entranceYear", year))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findByCriteria(criteria);
	}

	@Override
	public List<Log> findByTeacher(String schoolName, Short teacherNo) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass()).addOrder(Order.asc("logger"))
				.addOrder(Order.asc("loggee")).addOrder(Order.asc("timestamp"))
				.createCriteria("logger")
				.add(Restrictions.eq("teacherNumber", teacherNo))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findByCriteria(criteria);
	}

}
