package com.classtech.persistence.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.Student;
import com.classtech.persistence.dao.StudentDao;

@Repository
public class StudentDaoImpl extends GenericDaoImpl<Student> implements
		StudentDao {

	@Override
	public Student findByStudentNo(String schoolName, String year,
			String schoolClassName, Short studentNo) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass())
				.add(Restrictions.eq("studentNumber", studentNo))
				.createCriteria("schoolClass")
				.add(Restrictions.eq("name", schoolClassName))
				.createCriteria("year")
				.add(Restrictions.eq("entranceYear", year))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findUniqueByCriteria(criteria);
	}

}
