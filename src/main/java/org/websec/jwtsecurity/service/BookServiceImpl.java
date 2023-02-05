package org.websec.jwtsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> addBooks(List<Book> books, String username) {
		books.forEach(book -> book.setCreatedBy(username));
		return bookRepository.saveAll(books);
	}

}
