package org.websec.jwtsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.websec.jwtsecurity.model.manytomany.CourseRating;

//@Transactional
public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {

	@Query("SELECT crs.firstName FROM CourseRating cr inner join cr.student crs "
			+ "where cr.course.courseName = :courseId")
	List<String> findStudentsByCourseId(@Param("courseId") String courseName);
}
