package com.classtech.persistence.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.classtech.model.AwardType;
import com.classtech.model.Curriculum;
import com.classtech.model.CurriculumCategory;
import com.classtech.model.Facility;
import com.classtech.model.FacilityType;
import com.classtech.model.Grade;
import com.classtech.model.Guardian;
import com.classtech.model.GuardianRelationshipType;
import com.classtech.model.Log;
import com.classtech.model.Person;
import com.classtech.model.Schedule;
import com.classtech.model.School;
import com.classtech.model.SchoolClass;
import com.classtech.model.SeatingChartType;
import com.classtech.model.Student;
import com.classtech.model.Teacher;
import com.classtech.model.Year;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Service
public final class SchoolPopulator {

	Logger logger = LoggerFactory.getLogger(SchoolPopulator.class);

	@Autowired
	private SessionFactory sessionFactory;

	private final static String nameCharacters = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卡齐康伍余元卜顾孟平黄和穆萧尹姚邵堪汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴翟阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逯盖後桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐钟离宇文长孙慕容鲜于闾丘司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚闫法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟";
	private final static String alphabets = "abcdefghijklmnopqrstuvwxyz1234567890";
	private final static List<String> domains = Arrays.asList("gmail.com",
			"hotmail.com", "ymail.com", "yahoo.com", "126.com", "163.com",
			"sina.com", "qq.com");
	private final static List<String> mobilePrefix = Arrays.asList("130",
			"131", "132", "133", "134", "135", "136", "137", "138", "139",
			"147", "150", "151", "152", "157", "158", "159", "178", "182",
			"183", "184", "187", "188");
	private final static String chineseNum = "一二三四五六七八九十";

	private final static List<String> schoolNames = Arrays.asList("上海小学");
	private final static List<String> entranceYears = Arrays.asList("2009",
			"2010", "2011", "2012", "2013", "2014");
	private final static int currentYear = 2015;

	private final static List<String> facilityNames = Arrays.asList("设施");
	private final static String days = "12345";

	private Session session;
	private Transaction tx;

	private List<FacilityType> facilityTypes;
	private List<SeatingChartType> seatingChartTypes;
	private List<AwardType> awardTypes;
	private List<CurriculumCategory> curriculumCategories;
	private List<Grade> grades;
	private List<GuardianRelationshipType> guardianRelationshipTypes;

	private short teacherNo = 0;
	private short studentNo = 0;

	private int numOfClasses = 10;
	private int numOfTeachers = 100;
	private int numOfStudents = 40;
	private int numOfFacilities = 40;
	private int numOfCurriculumsPerTeacher = 5;
	private int maxNumOfLogsPerStudent = 10;

	private School school;
	private List<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private List<Student> students = new ArrayList<Student>();

	private List<Facility> facilities = new ArrayList<Facility>();
	private Multimap<Grade, Curriculum> curriculums = HashMultimap.create();

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"db-test.xml");

		SchoolPopulator populator = context.getBean(SchoolPopulator.class);
		for (String schoolName : schoolNames) {
			populator.run(schoolName);
		}
		((ConfigurableApplicationContext) context).close();
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	void init() {
		session = sessionFactory.openSession();
		this.facilityTypes = session.createCriteria(FacilityType.class)
				.setReadOnly(true).list();
		this.seatingChartTypes = session.createCriteria(SeatingChartType.class)
				.setReadOnly(true).list();
		this.awardTypes = session.createCriteria(AwardType.class)
				.setReadOnly(true).list();
		this.curriculumCategories = session
				.createCriteria(CurriculumCategory.class).setReadOnly(true)
				.list();
		this.grades = session.createCriteria(Grade.class).setReadOnly(true)
				.list();
		this.guardianRelationshipTypes = session
				.createCriteria(GuardianRelationshipType.class)
				.setReadOnly(true).list();
		session.close();
	}

	void run(String schoolName) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		clearTables();
		populate(schoolName);
		tx.commit();
		session.close();
	}

	private void clearTables() {
		Query q;
		q = session.createQuery("DELETE FROM Log");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Schedule");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Curriculum");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Facility");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Guardian");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Student");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM SchoolClass");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Year");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Teacher");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Person");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM Year");
		q.executeUpdate();
		q = session.createQuery("DELETE FROM School");
		q.executeUpdate();
	}

	private <T> T save(T t) {
		session.save(t);
		return t;
	}

	private void populate(String name) {
		logger.info("Start populating school");
		populateSchool(name);
		populateCurriculums();
		populateTeachers();
		populateYears();
		populateFacilities();
		populateSchedules();
		populateLogs();
		session.flush();
	}

	private void populateSchool(String name) {
		school = createSchool(name);
		session.flush();
	}

	private School createSchool(String name) {
		school = new School();
		school.setName(name);
		return save(school);
	}

	private void populateYears() {
		logger.info("Start populating years for school " + school);
		for (String entranceYear : entranceYears) {
			Year year = createYear(school, entranceYear);
			populateSchoolClasses(year);
		}
		session.flush();
	}

	private Year createYear(School school, String entranceYear) {
		Year schoolYear = new Year();
		schoolYear.setEntranceYear(entranceYear);
		schoolYear.setSchool(school);
		return save(schoolYear);
	}

	private void populateSchoolClasses(Year year) {
		logger.info("Start populating classes for year " + year);
		for (int i = 0; i < numOfClasses; i++) {
			SchoolClass schoolClass = createSchoolClass(year, i);
			populateStudents(schoolClass);
		}
		session.flush();
	}

	private SchoolClass createSchoolClass(Year year, int count) {
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setYear(year);
		schoolClass.setName(chineseNum.charAt(count) + "班");
		schoolClass.setManager(random(teachers));
		schoolClasses.add(schoolClass);
		return save(schoolClass);
	}

	private void populateTeachers() {
		logger.info("Start populating teachers for school " + school);
		for (int i = 0; i < numOfTeachers; i++) {
			createTeacher(school);
		}
		session.flush();
	}

	private Teacher createTeacher(School school) {
		Teacher teacher = new Teacher();
		teacher.setSchool(school);
		teacher.setTeacherNumber(teacherNo++);
		teacher.setPerson(createPerson());
		teacher.setCurriculums(randomList(curriculums.values(),
				numOfCurriculumsPerTeacher));
		teachers.add(teacher);
		return save(teacher);
	}

	private void populateStudents(SchoolClass schoolClass) {
		logger.info("Start populating students for class " + schoolClass);
		for (int i = 0; i < numOfStudents; i++) {
			Student student = createStudent(schoolClass, i);
			populateGuardians(student);
		}
		session.flush();
	}

	private Student createStudent(SchoolClass schoolClass, int count) {
		Student student = new Student();
		student.setSchoolClass(schoolClass);
		student.setStudentNumber((short) studentNo++);
		student.setSeatNumber((short) count);
		student.setPerson(createPerson());
		students.add(student);
		return save(student);
	}

	private void populateGuardians(Student student) {
		logger.info("Start populating guardians for student " + student);
		createGuardian(student);
		session.flush();
	}

	private Guardian createGuardian(Student student) {
		Guardian guardian = new Guardian();
		guardian.setStudent(student);
		guardian.setType(random(guardianRelationshipTypes));
		guardian.setPerson(createPerson());
		return save(guardian);
	}

	private Person createPerson() {
		Person person = new Person();
		person.setFirstName(generateChineseCharactor());
		person.setLastName(generateChineseCharactor());
		person.setEmail(generateEmail());
		person.setMobile(generateMobile());
		return save(person);
	}

	private void populateFacilities() {
		logger.info("Start populating facilities for school " + school);
		for (int i = 0; i < numOfFacilities; i++) {
			createFacility(school, i);
		}
		session.flush();
	}

	private Facility createFacility(School school, int count) {
		Facility facility = new Facility();
		facility.setSchool(school);
		facility.setName(random(facilityNames) + count);
		facility.setFacilityType(random(facilityTypes));
		facility.setSeatingChartType(random(seatingChartTypes));
		facilities.add(facility);
		return save(facility);
	}

	private void populateCurriculums() {
		logger.info("Start populating curriculums for school " + school);
		for (CurriculumCategory category : curriculumCategories) {
			for (Grade grade : grades) {
				createCurriculum(category, grade);
			}
		}
		session.flush();
	}

	private Curriculum createCurriculum(CurriculumCategory category, Grade grade) {
		Curriculum curriculum = new Curriculum();
		curriculum.setCategory(category);
		curriculum.setGrade(grade);
		curriculum.setName(grade.getName() + category.getName());
		curriculum.setShortName(category.getName());
		curriculums.put(grade, curriculum);
		return save(curriculum);
	}

	private void populateSchedules() {
		logger.info("Start populating schedules for school " + school);
		for (SchoolClass schoolClass : schoolClasses) {
			for (char day : days.toCharArray()) {
				for (int hour = 8; hour < 16; hour++) {
					createSchedule(schoolClass, day, hour);
				}
			}
		}
		session.flush();
	}

	private Schedule createSchedule(SchoolClass schoolClass, char day,
			int startHour) {
		Curriculum curriculum = random(curriculums
				.get(calculateGrade(schoolClass.getYear())));
		session.refresh(curriculum);
		Teacher teacher = random(curriculum.getTeachers());
		Schedule schedule = new Schedule();
		schedule.setSchoolClass(schoolClass);
		schedule.setTeacher(teacher);
		schedule.setDay(day);
		schedule.setStartTime(new LocalTime(startHour, 0));
		schedule.setEndTime(new LocalTime(startHour + 1, 0));
		schedule.setFacility(random(facilities));
		schedule.setCurriculum(curriculum);
		return save(schedule);
	}

	private void populateLogs() {
		logger.info("Start populating logs for school " + school);
		for (Student student : students) {
			for (int i = 0; i < new Random().nextInt(maxNumOfLogsPerStudent); i++) {
				createLog(student);
			}
		}
		session.flush();
	}

	private Log createLog(Student student) {
		Log log = new Log();
		log.setLoggee(student);
		log.setLogger(random(teachers));
		log.setAwardType(random(awardTypes));
		log.setContent("内容");
		DateTime time = new DateTime().withDate(2014,
				new Random().nextInt(12) + 1, new Random().nextInt(28) + 1);
		log.setTimestamp(time);
		return save(log);
	}

	private Grade calculateGrade(Year year) {
		int i = currentYear
				- Integer.valueOf(year.getEntranceYear()).intValue() - 1;
		return grades.get(i);
	}

	private String generateChineseCharactor() {
		return String.valueOf(random(nameCharacters));
	}

	private String generateEmail() {
		return randomChars(new Random().nextInt(10) + 6) + "@"
				+ random(domains);
	}

	private String generateMobile() {
		return random(mobilePrefix)
				+ String.format("%08d%n", new Random().nextInt(100000000));
	}

	private String randomChars(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(randomChar());
		}
		return sb.toString();
	}

	private char randomChar() {
		return random(alphabets);
	}

	private <T> T random(Collection<T> c) {
		return new ArrayList<T>(c).get(new Random().nextInt(c.size()));
	}

	private <T> List<T> randomList(Collection<T> c, int count) {
		List<T> result = new ArrayList<T>(count);
		List<T> list = new ArrayList<T>(c);
		Collections.shuffle(list, new Random());
		for (int i = 0; i < count && i < list.size(); i++) {
			result.add(list.get(i));
		}
		return result;
	}

	private char random(String str) {
		return str.charAt(new Random().nextInt(str.length()));
	}

}
