package com.classtech.persistence.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.Teacher;
import com.classtech.persistence.dao.TeacherDao;

@Repository
public class TeacherDaoImpl extends GenericDaoImpl<Teacher> implements
		TeacherDao {

	@Override
	public List<Teacher> findBySchool(String schoolName) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass())
				.addOrder(Order.asc("teacherNumber")).createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findByCriteria(criteria);
	}

	@Override
	public Teacher findByTeacherNo(String schoolName, Short teacherNo) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass())
				.add(Restrictions.eq("teacherNumber", teacherNo))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findUniqueByCriteria(criteria);
	}

}
