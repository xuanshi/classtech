package com.classtech.persistence.dao.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.classtech.persistence.dao.TypeDao;

public abstract class TypeDaoImpl<T> extends GenericDaoImpl<T> implements
		TypeDao<T> {

	@Override
	public T findByName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findUniqueByCriteria(criterion);
	}

}
