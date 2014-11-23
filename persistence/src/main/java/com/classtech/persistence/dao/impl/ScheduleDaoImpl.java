package com.classtech.persistence.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.classtech.model.Schedule;
import com.classtech.persistence.dao.ScheduleDao;

@Repository
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements
		ScheduleDao {

	@Override
	public List<Schedule> findByName(String schoolName, String year,
			String schoolClassName) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(getPersistentClass()).addOrder(Order.asc("day"))
				.addOrder(Order.asc("startTime")).createCriteria("schoolClass")
				.add(Restrictions.eq("name", schoolClassName))
				.createCriteria("year")
				.add(Restrictions.eq("entranceYear", year))
				.createCriteria("school")
				.add(Restrictions.eq("name", schoolName));
		return findByCriteria(criteria);
	}

}
