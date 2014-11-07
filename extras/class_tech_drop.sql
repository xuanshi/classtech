-- Created by Vertabelo (http://vertabelo.com)
-- Script type: drop
-- Scope: [tables, references, sequences, views, procedures]
-- Generated at Tue Oct 21 06:41:47 UTC 2014





-- foreign keys
DROP SEQUENCE seq_object_id;


DROP SEQUENCE seq_person_id;


DROP SEQUENCE seq_type_id;





-- foreign keys
ALTER TABLE class DROP CONSTRAINT class_year;

ALTER TABLE curriculum DROP CONSTRAINT curriculum_curriculum_category;

ALTER TABLE curriculum DROP CONSTRAINT curriculum_grade;

ALTER TABLE facility DROP CONSTRAINT facility_facility_type;

ALTER TABLE guardian DROP CONSTRAINT guardian_guardian_relationship;

ALTER TABLE guardian DROP CONSTRAINT guardian_student;

ALTER TABLE facility DROP CONSTRAINT location_school;

ALTER TABLE facility DROP CONSTRAINT location_seating_chart_type;

ALTER TABLE log DROP CONSTRAINT log_award_type;

ALTER TABLE log DROP CONSTRAINT log_student;

ALTER TABLE log DROP CONSTRAINT log_teacher;

ALTER TABLE guardian DROP CONSTRAINT parent_person;

ALTER TABLE schedule DROP CONSTRAINT schedule_class;

ALTER TABLE schedule DROP CONSTRAINT schedule_curriculum;

ALTER TABLE schedule DROP CONSTRAINT schedule_facility;

ALTER TABLE student DROP CONSTRAINT student_class;

ALTER TABLE student DROP CONSTRAINT student_person;

ALTER TABLE teacher_curriculum DROP CONSTRAINT teacher_curriculum_curriculum;

ALTER TABLE teacher_curriculum DROP CONSTRAINT teacher_curriculum_teacher;

ALTER TABLE teacher DROP CONSTRAINT teacher_person;

ALTER TABLE teacher DROP CONSTRAINT teacher_school;

ALTER TABLE "user" DROP CONSTRAINT user_person;

ALTER TABLE user_role DROP CONSTRAINT user_role_role;

ALTER TABLE user_role DROP CONSTRAINT user_role_user;

ALTER TABLE year DROP CONSTRAINT year_school;





-- tables
DROP TABLE award_type;
DROP TABLE class;
DROP TABLE curriculum;
DROP TABLE curriculum_category;
DROP TABLE facility;
DROP TABLE facility_type;
DROP TABLE grade;
DROP TABLE guardian;
DROP TABLE guardian_relationship_type;
DROP TABLE log;
DROP TABLE person;
DROP TABLE role;
DROP TABLE schedule;
DROP TABLE school;
DROP TABLE seating_chart_type;
DROP TABLE student;
DROP TABLE teacher;
DROP TABLE teacher_curriculum;
DROP TABLE "user";
DROP TABLE user_role;
DROP TABLE year;




-- End of file.

