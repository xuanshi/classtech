package com.classtech.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.classtech.model.Schedule;
import com.classtech.persistence.dao.ScheduleDao;

@Repository
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements
		ScheduleDao {

}
