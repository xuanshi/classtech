package com.classtech.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.model.Log;
import com.classtech.persistence.dao.LogDao;
import com.classtech.service.LogManager;

@RestController
@RequestMapping(value = "/logs")
public class LogsController {

	@Autowired
	private LogDao logDao;
	@Autowired
	private LogManager logManager;
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
	public List<LogDto> getLogs(@PathVariable String schoolName,
			@PathVariable Short teacherNo) {
		return logsConverter.toLogDtos(logDao.findByTeacher(schoolName,
				teacherNo));
	}

	@RequestMapping(value = "{schoolName}/{teacherNo}", method = RequestMethod.POST)
	public LogDto log(@PathVariable String schoolName,
			@PathVariable Short teacherNo,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "class") String schoolClassName,
			@RequestParam(value = "student") Short studentNo,
			@RequestParam(value = "award") String awardType,
			@RequestParam(value = "content") String content) {
		Log log = logManager.insert(schoolName, teacherNo, year,
				schoolClassName, studentNo, awardType, content);
		return logsConverter.toLogDto(log);

	}
}
