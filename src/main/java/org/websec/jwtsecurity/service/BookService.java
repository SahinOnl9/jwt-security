package org.websec.jwtsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.model.SearchCriteria;

public interface BookService {

	List<Book> addBooks(List<Book> books, String username);

	Page<Book> getBooks(Pageable pageable);
	
	List<Book> searchBooksByCriteria(Pageable pageable, SearchCriteria criteria);
}
