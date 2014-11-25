package com.classtech.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.persistence.dao.SchoolDao;

@RestController
@RequestMapping(value = "/schools")
public class SchoolController {

	@Autowired
	private SchoolConverter schoolConverter;

	@Autowired
	private SchoolDao schoolDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<SchoolDto> getSchools() {
		List<SchoolDto> dtos = schoolConverter
				.toSchoolDtos(schoolDao.findAll());
		return dtos;
	}

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public SchoolDto getSchool(@PathVariable String name) {
		return schoolConverter.toSchoolDto(schoolDao.findByName(name));
	}
}
