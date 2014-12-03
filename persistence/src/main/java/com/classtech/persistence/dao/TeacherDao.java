package com.classtech.persistence.dao;

import java.util.List;

import com.classtech.model.Teacher;

public interface TeacherDao extends GenericDao<Teacher> {

	List<Teacher> findBySchool(String schoolName);

	Teacher findByTeacherNo(String schoolName, Short teacherNo);
}
