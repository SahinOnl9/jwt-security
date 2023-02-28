package org.websec.jwtsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.model.SearchCriteria;
import org.websec.jwtsecurity.repository.BookDao;
import org.websec.jwtsecurity.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final BookDao bookDao;

	@Override
	public List<Book> addBooks(List<Book> books, String username) {
		books.forEach(book -> book.setCreatedBy(username));
		return bookRepository.saveAll(books);
	}

	@Override
	public Page<Book> getBooks(Pageable pageable) {
		Page<Book> findAll = bookRepository.findAll(pageable);
		return findAll;
	}

	@Override
	public List<Book> searchBooksByCriteria(Pageable pageable, SearchCriteria criteria) {
		return bookDao.findBooksBySearchCriteria(pageable, criteria);
	}

}
