package com.classtech.rest.school;

import java.io.Serializable;
import java.util.List;

public class SchoolDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public String name;
	public List<YearDto> years;
}
