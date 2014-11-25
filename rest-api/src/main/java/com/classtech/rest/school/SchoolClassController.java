package com.classtech.rest.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.persistence.dao.SchoolClassDao;

@RestController
@RequestMapping(value = "/classes")
public class SchoolClassController {

	@Autowired
	private SchoolClassDao schoolClassDao;

	@Autowired
	private SchoolConverter schoolConverter;

	@RequestMapping(value = "{schoolName}/{yearName}/{schoolClassName}", method = RequestMethod.GET)
	public SchoolClassDetailDto getSchoolClass(@PathVariable String schoolName,
			@PathVariable String yearName, @PathVariable String schoolClassName) {
		return schoolConverter.toSchoolClassDetialDto(schoolClassDao
				.findByName(schoolName, yearName, schoolClassName));
	}

}
