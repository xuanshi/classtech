-- Insert types
INSERT INTO award_type(name, positive) VALUES
     ('积极参与', true),
     ('消极表现', true);

INSERT INTO facility_type(name) VALUES
     ('教室'),
     ('操场');

INSERT INTO guardian_relationship_type(name) VALUES
     ('父亲'),
     ('母亲');

INSERT INTO seating_chart_type(name, "row", col) VALUES
     ('标准', 8, 8);

INSERT INTO curriculum_category(name) VALUES
     ('语文'),
     ('数学'),
     ('英语'),
     ('体育'),
     ('电脑'),
     ('音乐');

-- Insert entities
INSERT INTO school(id, name) VALUES (1, '上海南洋模范中学');
INSERT INTO year(id, year, school_id) VALUES (2, '2015', 1);
INSERT INTO class(id, name, year_id) VALUES (3, '一班', 2);

INSERT INTO person(id, first_name, last_name, email, mobile) VALUES 
	(101, '烜', '时', 'bennieys@gmail.com', '+8613764412113');
INSERT INTO student(person_id, student_no, seat_no, class_id) 
	VALUES (101, 1, 1, 3);

INSERT INTO person(id, first_name, last_name, email, mobile)
	VALUES (102, '张', '张', 'sample1@gmail.com', '+861370000000');
INSERT INTO teacher(person_id, teacher_no, school_id)
	VALUES (102, 1, 1);

INSERT INTO person(id, first_name, last_name, email, mobile) VALUES
	(103, '爸', '时', 'sample2@gmail.com', '+861380000000');
INSERT INTO guardian(person_id, guardian_relationship_type_id, student_person_id) VALUES
	(103, (SELECT id FROM guardian_relationship_type WHERE name = '父亲'), 101);


