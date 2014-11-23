package com.classtech.persistence.dao.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.School;
import com.classtech.persistence.dao.SchoolDao;

@Repository
public class SchoolDaoImpl extends GenericDaoImpl<School> implements SchoolDao {

	@Override
	public School findByName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findUniqueByCriteria(criterion);
	}

}
