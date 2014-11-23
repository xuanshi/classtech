package com.classtech.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.persistence.dao.ScheduleDao;

@RestController
@RequestMapping(value = "/schedules")
public class SchedulesController {

	@Autowired
	private ScheduleDao scheduleDao;

	@Autowired
	private SchedulesConverter schedulesConverter;

	@RequestMapping(value = "{schoolName}/{yearName}/{schoolClassName}", method = RequestMethod.GET)
	public List<ScheduleDto> getSchoolClassSchedule(
			@PathVariable String schoolName, @PathVariable String yearName,
			@PathVariable String schoolClassName) {
		return schedulesConverter.toSchoolScheduleDtos(scheduleDao.findByName(
				schoolName, yearName, schoolClassName));
	}

}
