package com.classtech.persistence.dao.impl;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.SchoolClass;
import com.classtech.persistence.dao.SchoolClassDao;

@Repository
public class SchoolClassDaoImpl extends GenericDaoImpl<SchoolClass> implements
		SchoolClassDao {

	@Override
	public SchoolClass findByName(String schoolName, String year,
			String schoolClassName) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass())
				.add(Restrictions.eq("name", schoolClassName))
				.setFetchMode("schedules", FetchMode.SELECT)
				.createCriteria("year")
				.add(Restrictions.eq("entranceYear", year))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findUniqueByCriteria(criteria);
	}
}
