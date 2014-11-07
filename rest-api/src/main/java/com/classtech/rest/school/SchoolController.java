package com.classtech.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.service.SchoolService;

@RestController
@RequestMapping(value = "/")
public class SchoolController {

	@Autowired
	SchoolConverter schoolConverter;

	@Autowired
	SchoolService schoolService;

	@RequestMapping(value = "schools", method = RequestMethod.GET)
	public List<SchoolDto> getSchools() {
		List<SchoolDto> dtos = schoolConverter.toSchoolDtos(schoolService
				.getAllSchools());
		return dtos;
	}
}
