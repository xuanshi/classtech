package com.classtech.rest.school;

import java.io.Serializable;
import java.util.List;

public class YearDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public String entranceYear;
	public List<SchoolClassDto> classes;
}
