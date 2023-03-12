package org.websec.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websec.jwtsecurity.model.manytomany.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
