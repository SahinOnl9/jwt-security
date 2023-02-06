package org.websec.jwtsecurity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/save-all")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books, HttpServletRequest request) {
		String username = request.getAttribute("username").toString();
		log.info("User: {}", username);
		return ResponseEntity.created(null).body(bookService.addBooks(books, username));
	}
}
