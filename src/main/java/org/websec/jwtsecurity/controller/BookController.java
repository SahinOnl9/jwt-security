package org.websec.jwtsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.service.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/save-all")
//	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) {
//		JWTHelper helper = new JWTHelper(authToken);
//		String username = helper.getUsername();
		return ResponseEntity.created(null).body(bookService.addBooks(books, "abc"));
	}
}
