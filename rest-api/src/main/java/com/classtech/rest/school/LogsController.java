package com.classtech.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.persistence.dao.LogDao;

@RestController
@RequestMapping(value = "/logs")
public class LogsController {

	@Autowired
	private LogDao logDao;
	@Autowired
	private LogsConverter logsConverter;

	@RequestMapping(value = "{schoolName}/{yearName}/{schoolClassName}", method = RequestMethod.GET)
	public List<LogDto> getLogs(@PathVariable String schoolName,
			@PathVariable String yearName, @PathVariable String schoolClassName) {
		return logsConverter.toLogDtos(logDao.findBySchoolClass(schoolName,
				yearName, schoolClassName));
	}

	@RequestMapping(value = "{schoolName}/{yearName}/{schoolClassName}/{studentNo}", method = RequestMethod.GET)
	public List<LogDto> getLogs(@PathVariable String schoolName,
			@PathVariable String yearName,
			@PathVariable String schoolClassName, Short studentNo) {
		return logsConverter.toLogDtos(logDao.findByStudent(schoolName,
				yearName, schoolClassName, studentNo));
	}

	@RequestMapping(value = "{schoolName}/{teacherNo}", method = RequestMethod.GET)
	public List<LogDto> getLogs(@PathVariable String schoolName, Short teacherNo) {
		return logsConverter.toLogDtos(logDao.findByTeacher(schoolName,
				teacherNo));
	}
}
