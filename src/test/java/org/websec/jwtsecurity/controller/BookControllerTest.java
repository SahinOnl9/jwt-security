package org.websec.jwtsecurity.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.websec.jwtsecurity.service.BookService;

class BookControllerTest {

	@Mock
	private BookService bookService;

	@Mock
	private HttpServletRequest httpServletRequest;

	private BookController bookController;

	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		bookController = new BookController(bookService);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddBooks() {
		when(httpServletRequest.getAttribute(Mockito.anyString())).thenReturn("sahin");
		bookController.addBooks(Mockito.anyList(), httpServletRequest);
		verify(bookService).addBooks(Mockito.anyList(), Mockito.anyString());
	}

	@Test
	void testGetBooks() {
		bookController.getBooks(0, 5);
		verify(bookService).getBooks(Mockito.any(Pageable.class));
	}

}
