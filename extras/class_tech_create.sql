-- Created by Vertabelo (http://vertabelo.com)
-- Script type: create
-- Scope: [tables, references, sequences, views, procedures]
-- Generated at Tue Oct 21 06:41:51 UTC 2014





-- sequences
-- Sequence: seq_object_id



CREATE SEQUENCE seq_object_id
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000000000 
      
      NO CYCLE
      
;


-- Sequence: seq_person_id



CREATE SEQUENCE seq_person_id
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000000000 
      
      NO CYCLE
      
;


-- Sequence: seq_type_id



CREATE SEQUENCE seq_type_id
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000 
      
      NO CYCLE
      
;


-- tables
-- Table: award_type
CREATE TABLE award_type (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    positive boolean  NOT NULL,
    CONSTRAINT award_type_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT award_type_pk PRIMARY KEY (id)
);



-- Table: class
CREATE TABLE class (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    name varchar(200)  NOT NULL,
    year_id bigint  NOT NULL,
    manager_id bigint NOT NULL,
    CONSTRAINT class_ak_year_name UNIQUE (name, year_id) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT class_pk PRIMARY KEY (id)
);



-- Table: curriculum
CREATE TABLE curriculum (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    name varchar(200)  NOT NULL,
    short_name varchar(10)  NOT NULL,
    curriculum_category_id bigint  NOT NULL,
    grade_id bigint  NOT NULL,
    CONSTRAINT curriculum_ak_name_category_grade UNIQUE (name, curriculum_category_id, grade_id) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT curriculum_pk PRIMARY KEY (id)
);



-- Table: curriculum_category
CREATE TABLE curriculum_category (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    CONSTRAINT curriculum_category_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT curriculum_category_pk PRIMARY KEY (id)
);



-- Table: facility
CREATE TABLE facility (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    name varchar(200)  NOT NULL,
    school_id bigint  NOT NULL,
    seating_chart_type_id bigint  NOT NULL,
    facility_type_id bigint  NOT NULL,
    CONSTRAINT facility_ak_school_name UNIQUE (school_id, name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT facility_pk PRIMARY KEY (id)
);



-- Table: facility_type
CREATE TABLE facility_type (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    CONSTRAINT facility_type_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT facility_type_pk PRIMARY KEY (id)
);



-- Table: grade
CREATE TABLE grade (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    CONSTRAINT grade_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT grade_pk PRIMARY KEY (id)
);



-- Table: guardian
CREATE TABLE guardian (
    person_id bigint  NOT NULL,
    guardian_relationship_type_id bigint  NOT NULL,
    student_person_id bigint  NOT NULL,
    CONSTRAINT guardian_ak_student_relation UNIQUE (guardian_relationship_type_id, student_person_id) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT guardian_pk PRIMARY KEY (person_id)
);



-- Table: guardian_relationship_type
CREATE TABLE guardian_relationship_type (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    CONSTRAINT guardian_relationship_type_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT guardian_relationship_type_pk PRIMARY KEY (id)
);



-- Table: log
CREATE TABLE log (
    id bigserial  NOT NULL,
    timestamp timestamp  NOT NULL,
    content varchar(4000)  NOT NULL,
    award_type_id bigint  NOT NULL,
    student_id bigint  NOT NULL,
    teacher_id bigint  NOT NULL,
    CONSTRAINT log_pk PRIMARY KEY (id)
);



-- Table: person
CREATE TABLE person (
    id bigint  NOT NULL DEFAULT nextval('seq_person_id'),
    first_name varchar(12)  NOT NULL,
    last_name varchar(12)  NOT NULL,
    email varchar(320)  NOT NULL,
    mobile varchar(20)  NOT NULL,
    CONSTRAINT person_ak_mobile UNIQUE (mobile) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT person_ak_email UNIQUE (email) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT person_pk PRIMARY KEY (id)
);



-- Table: role
CREATE TABLE role (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    CONSTRAINT role_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT role_pk PRIMARY KEY (id)
);



-- Table: schedule
CREATE TABLE schedule (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    day char(1)  NOT NULL,
    start_time time  NOT NULL,
    end_time time  NOT NULL,
    class_id bigint  NOT NULL,
    curriculum_id bigint  NOT NULL,
    facility_id bigint  NOT NULL,
    CONSTRAINT schedule_pk PRIMARY KEY (id)
);



-- Table: school
CREATE TABLE school (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    name varchar(200)  NOT NULL,
    CONSTRAINT school_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT school_pk PRIMARY KEY (id)
);



-- Table: seating_chart_type
CREATE TABLE seating_chart_type (
    id bigint  NOT NULL DEFAULT nextval('seq_type_id'),
    name varchar(50)  NOT NULL,
    row smallint  NOT NULL,
    col smallint  NOT NULL,
    CONSTRAINT seating_chart_type_ak_name UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT seating_chart_type_pk PRIMARY KEY (id)
);



-- Table: student
CREATE TABLE student (
    person_id bigint  NOT NULL,
    student_no smallint  NOT NULL,
    seat_no smallint  NOT NULL,
    class_id bigint  NOT NULL,
    CONSTRAINT student_ak_class_student UNIQUE (class_id, student_no) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT student_ak_class_seat UNIQUE (class_id, seat_no) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT student_pk PRIMARY KEY (person_id)
);



-- Table: teacher
CREATE TABLE teacher (
    person_id bigint  NOT NULL,
    teacher_no smallint  NOT NULL,
    school_id bigint  NOT NULL,
    CONSTRAINT teacher_ak_school_teacher UNIQUE (school_id, teacher_no) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT teacher_pk PRIMARY KEY (person_id)
);



-- Table: teacher_curriculum
CREATE TABLE teacher_curriculum (
    curriculum_id bigint  NOT NULL,
    teacher_id bigint  NOT NULL,
    CONSTRAINT teacher_curriculum_pk PRIMARY KEY (curriculum_id,teacher_id)
);



-- Table: "user"
CREATE TABLE "user" (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    mobile varchar(20)  NOT NULL,
    password varchar(100)  NOT NULL,
    CONSTRAINT user_ak_mobile UNIQUE (mobile) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT user_pk PRIMARY KEY (id)
);



-- Table: user_role
CREATE TABLE user_role (
    user_id bigint  NOT NULL,
    role_id bigint  NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (user_id,role_id)
);



-- Table: year
CREATE TABLE year (
    id bigint  NOT NULL DEFAULT nextval('seq_object_id'),
    year varchar(4)  NOT NULL CHECK (year ~ '(\d{4})'),
    school_id bigint  NOT NULL,
    CONSTRAINT year_ak_school_year UNIQUE (year, school_id) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT year_pk PRIMARY KEY (id)
);







-- foreign keys
-- Reference:  class_year (table: class)


ALTER TABLE class ADD CONSTRAINT class_year 
    FOREIGN KEY (year_id)
    REFERENCES year (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  class_teacher (table: class)


ALTER TABLE class ADD CONSTRAINT class_manager 
    FOREIGN KEY (manager_id)
    REFERENCES teacher (person_id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  curriculum_curriculum_category (table: curriculum)


ALTER TABLE curriculum ADD CONSTRAINT curriculum_curriculum_category 
    FOREIGN KEY (curriculum_category_id)
    REFERENCES curriculum_category (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  curriculum_grade (table: curriculum)


ALTER TABLE curriculum ADD CONSTRAINT curriculum_grade 
    FOREIGN KEY (grade_id)
    REFERENCES grade (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  facility_facility_type (table: facility)


ALTER TABLE facility ADD CONSTRAINT facility_facility_type 
    FOREIGN KEY (facility_type_id)
    REFERENCES facility_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  guardian_guardian_relationship (table: guardian)


ALTER TABLE guardian ADD CONSTRAINT guardian_guardian_relationship 
    FOREIGN KEY (guardian_relationship_type_id)
    REFERENCES guardian_relationship_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  guardian_student (table: guardian)


ALTER TABLE guardian ADD CONSTRAINT guardian_student 
    FOREIGN KEY (student_person_id)
    REFERENCES student (person_id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  location_school (table: facility)


ALTER TABLE facility ADD CONSTRAINT location_school 
    FOREIGN KEY (school_id)
    REFERENCES school (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  location_seating_chart_type (table: facility)


ALTER TABLE facility ADD CONSTRAINT location_seating_chart_type 
    FOREIGN KEY (seating_chart_type_id)
    REFERENCES seating_chart_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  log_award_type (table: log)


ALTER TABLE log ADD CONSTRAINT log_award_type 
    FOREIGN KEY (award_type_id)
    REFERENCES award_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  log_student (table: log)


ALTER TABLE log ADD CONSTRAINT log_student 
    FOREIGN KEY (student_id)
    REFERENCES student (person_id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  log_teacher (table: log)


ALTER TABLE log ADD CONSTRAINT log_teacher 
    FOREIGN KEY (teacher_id)
    REFERENCES teacher (person_id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  parent_person (table: guardian)


ALTER TABLE guardian ADD CONSTRAINT parent_person 
    FOREIGN KEY (person_id)
    REFERENCES person (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  schedule_class (table: schedule)


ALTER TABLE schedule ADD CONSTRAINT schedule_class 
    FOREIGN KEY (class_id)
    REFERENCES class (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  schedule_curriculum (table: schedule)


ALTER TABLE schedule ADD CONSTRAINT schedule_curriculum 
    FOREIGN KEY (curriculum_id)
    REFERENCES curriculum (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  schedule_facility (table: schedule)


ALTER TABLE schedule ADD CONSTRAINT schedule_facility 
    FOREIGN KEY (facility_id)
    REFERENCES facility (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  student_class (table: student)


ALTER TABLE student ADD CONSTRAINT student_class 
    FOREIGN KEY (class_id)
    REFERENCES class (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  student_person (table: student)


ALTER TABLE student ADD CONSTRAINT student_person 
    FOREIGN KEY (person_id)
    REFERENCES person (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  teacher_curriculum_curriculum (table: teacher_curriculum)


ALTER TABLE teacher_curriculum ADD CONSTRAINT teacher_curriculum_curriculum 
    FOREIGN KEY (curriculum_id)
    REFERENCES curriculum (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  teacher_curriculum_teacher (table: teacher_curriculum)


ALTER TABLE teacher_curriculum ADD CONSTRAINT teacher_curriculum_teacher 
    FOREIGN KEY (curriculum_id)
    REFERENCES teacher (person_id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  teacher_person (table: teacher)


ALTER TABLE teacher ADD CONSTRAINT teacher_person 
    FOREIGN KEY (person_id)
    REFERENCES person (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  teacher_school (table: teacher)


ALTER TABLE teacher ADD CONSTRAINT teacher_school 
    FOREIGN KEY (school_id)
    REFERENCES school (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  user_person (table: "user")


ALTER TABLE "user" ADD CONSTRAINT user_person 
    FOREIGN KEY (mobile)
    REFERENCES person (mobile)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  user_role_role (table: user_role)


ALTER TABLE user_role ADD CONSTRAINT user_role_role 
    FOREIGN KEY (role_id)
    REFERENCES role (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  user_role_user (table: user_role)


ALTER TABLE user_role ADD CONSTRAINT user_role_user 
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  year_school (table: year)


ALTER TABLE year ADD CONSTRAINT year_school 
    FOREIGN KEY (school_id)
    REFERENCES school (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;









-- End of file.

