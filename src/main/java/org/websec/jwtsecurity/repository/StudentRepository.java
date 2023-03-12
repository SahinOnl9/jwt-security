package org.websec.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websec.jwtsecurity.model.manytomany.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
