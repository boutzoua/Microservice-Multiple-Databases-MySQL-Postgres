package com.kindsonthegenius.epm2;

import com.kindsonthegenius.epm2.students.Student;
import com.kindsonthegenius.epm2.students.StudentRepository;
import com.kindsonthegenius.epm2.courses.Course;
import com.kindsonthegenius.epm2.courses.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackages = "{com.kindsonthegenius.epm2.*}")
@RestController
@EnableEurekaClient
public class Epm2Application {

	public static void main(String[] args) {
		SpringApplication.run(Epm2Application.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;



	@PostConstruct
	public void addStudentsData() {
			studentRepository.saveAll(Stream.of(
					new Student(101, "A student needing a Course"),
					new Student(102, "Software Eng Course"))
					.collect(Collectors.toList()));
	}
	@GetMapping("/getStudents")
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}


	@PostConstruct
	public void addCoursesData() {
		courseRepository.saveAll(Stream.of(
				new Course(101, "The first appointment ever"),
				new Course(102, "Appointment with a Student"))
				.collect(Collectors.toList()));
	}
	@GetMapping("/getCourses")
	public List<Course> getcourses(){
		return courseRepository.findAll();
	}


}
