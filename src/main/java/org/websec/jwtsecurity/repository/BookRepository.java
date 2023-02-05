package org.websec.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websec.jwtsecurity.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
