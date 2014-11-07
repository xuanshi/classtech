package com.classtech.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Log extends BaseEntity {

	private DateTime timestamp;

	private String content;

	private AwardType awardType;

	private Teacher logger;

	private Student loggee;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "award_type_id")
	public AwardType getAwardType() {
		return awardType;
	}

	public void setAwardType(AwardType awardType) {
		this.awardType = awardType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	public Teacher getLogger() {
		return logger;
	}

	public void setLogger(Teacher logger) {
		this.logger = logger;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	public Student getLoggee() {
		return loggee;
	}

	public void setLoggee(Student loggee) {
		this.loggee = loggee;
	}

}
