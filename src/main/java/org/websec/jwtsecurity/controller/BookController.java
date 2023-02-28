package org.websec.jwtsecurity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.model.SearchCriteria;
import org.websec.jwtsecurity.service.BookService;
import org.websec.jwtsecurity.validator.BookValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@Slf4j
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping("/save-all")
	@Operation(summary = "Saves list of books", security = { @SecurityRequirement(name = "JWT Token") })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Books added", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }) })
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books, HttpServletRequest request) {
		String username = request.getAttribute("username").toString();
		BookValidator.validateBooks(books);
		log.info("User: {}", username);
		return ResponseEntity.created(null).body(bookService.addBooks(books, username));
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "40") int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("title"));
		return ResponseEntity.ok(bookService.getBooks(pageable));
	}

	@PostMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "40") int size, 
			@RequestBody(required = false) SearchCriteria searchCriteria) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("S.C: "+searchCriteria);
		return ResponseEntity.ok(bookService.searchBooksByCriteria(pageable, searchCriteria));
	}
}
