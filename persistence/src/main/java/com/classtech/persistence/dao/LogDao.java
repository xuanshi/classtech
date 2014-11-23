package com.classtech.persistence.dao;

import java.util.List;

import com.classtech.model.Log;

public interface LogDao extends GenericDao<Log> {

	List<Log> findBySchoolClass(String schoolName, String year, String schoolClassName);

	List<Log> findByStudent(String schoolName, String year,
			String schoolClassName, Short studentNo);

	List<Log> findByTeacher(String schoolName, Short teacherNo);
}
