package org.websec.jwtsecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.websec.jwtsecurity.repository.CourseRatingRepository;
import org.websec.jwtsecurity.repository.CourseRepository;
import org.websec.jwtsecurity.repository.StudentRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseRatingRepository courseRatingRepository;

	@Override
	public void run(String... args) throws Exception {

		List<String> result = courseRatingRepository.findStudentsByCourseId("Artificial Intelligence");

		System.out.println("*************************************");
		result.forEach(System.out::println);
		System.out.println("*************************************");
	}

}
