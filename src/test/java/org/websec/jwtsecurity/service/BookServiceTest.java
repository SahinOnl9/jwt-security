package org.websec.jwtsecurity.service;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.repository.BookDao;
import org.websec.jwtsecurity.repository.BookRepository;

class BookServiceTest {

	private BookService underTest;

	@Mock
	private BookRepository bookRepository;
	@Mock
	private BookDao bookDao;

	private AutoCloseable autoCloseable;

	@Mock
	private Pageable pageable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		underTest = new BookServiceImpl(bookRepository, bookDao);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddBooks() {
		List<Book> books = new ArrayList<>();
		books.add(Book.builder().author("jkr").isbn(1l).title("hps").build());
		underTest.addBooks(books, "admin");
		verify(bookRepository).saveAll(books);
	}

	@Test
	void testGetBooks() {
		underTest.getBooks(pageable);
		verify(bookRepository).findAll(pageable);
	}

}
