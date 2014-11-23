package com.classtech.rest.school;

import java.io.Serializable;

public class LogDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public String timestamp;
	public TeacherDto logger;
	public StudentDto loggee;
	public String awardType;
	public String Content;
}
