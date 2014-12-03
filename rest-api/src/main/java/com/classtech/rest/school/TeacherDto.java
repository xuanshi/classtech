package com.classtech.rest.school;

import java.io.Serializable;

public class TeacherDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public PersonDto person;
	public Short teacherNo;
	public String schoolName;
}
