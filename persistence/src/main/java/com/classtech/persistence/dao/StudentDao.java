package com.classtech.persistence.dao;

import com.classtech.model.Student;

public interface StudentDao extends GenericDao<Student> {

	Student findByStudentNo(String schoolName, String year,
			String schoolClassName, Short studentNo);
}
