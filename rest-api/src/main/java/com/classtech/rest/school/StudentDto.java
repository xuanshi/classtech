package com.classtech.rest.school;

import java.io.Serializable;

public class StudentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public PersonDto person;
	public Short studentNo;
	public Short seatNo;
}
