package com.classtech.persistence.dao;

import java.util.List;

import com.classtech.model.Schedule;

public interface ScheduleDao extends GenericDao<Schedule> {

	List<Schedule> findByName(String schoolName, String year,
			String schoolClassName);

	List<Schedule> findByTeacher(String schoolName, Short teacherNo);
}
