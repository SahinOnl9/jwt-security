package org.websec.jwtsecurity;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.websec.jwtsecurity.model.manytomany.Course;
import org.websec.jwtsecurity.model.manytomany.CourseRating;
import org.websec.jwtsecurity.model.manytomany.Student;
import org.websec.jwtsecurity.repository.CourseRatingRepository;
import org.websec.jwtsecurity.repository.CourseRepository;
import org.websec.jwtsecurity.repository.StudentRepository;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRatingRepository courseRatingRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student = studentRepository.findById(14l).get();
		Course course = courseRepository.findById(16l).get();
		
		CourseRating courseRating = CourseRating.builder().student(student).course(course).build();
		
		courseRatingRepository.save(courseRating);
	}

//	public static void main(String[] args) {
//		File file = new File("C:\\Users\\sk_sa\\Downloads\\Documents\\tableConvert.com_qcs7nt.csv");
//		CSVParser csvParser = new CSVParserBuilder().withQuoteChar('"').withSeparator(',').build();
//		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(csvParser).withSkipLines(1)
//				.build()) {
//			List<String[]> readAll = csvReader.readAll();
//			System.out.println("Size: " + readAll.size());
//			readAll.stream().filter(line -> line.length != 1)
//			.forEach(line -> {
//				System.out.println(line[1].strip().replace("\"", ""));
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
