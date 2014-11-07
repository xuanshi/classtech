package com.classtech.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.classtech.model.Student;
import com.classtech.persistence.dao.StudentDao;

@Repository
public class StudentDaoImpl extends GenericDaoImpl<Student> implements
		StudentDao {

}
