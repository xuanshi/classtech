package com.classtech.rest.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.Log;

@Service
public class LogsConverter {

	@Autowired
	private PersonConverter personConverter;

	public List<LogDto> toLogDtos(List<Log> logs) {
		List<LogDto> dtos = new ArrayList<LogDto>();
		for (Log log : logs) {
			dtos.add(toLogDto(log));
		}
		return dtos;
	}

	public LogDto toLogDto(Log log) {
		LogDto dto = new LogDto();
		dto.timestamp = log.getTimestamp().toString();
		dto.awardType = log.getAwardType().getName();
		dto.logger = personConverter.toTeacherDto(log.getLogger());
		dto.loggee = personConverter.toStudentDto(log.getLoggee());
		dto.Content = log.getContent();
		return dto;
	}
}
