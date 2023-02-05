package org.websec.jwtsecurity.service;

import java.util.List;

import org.websec.jwtsecurity.model.Book;

public interface BookService {

	List<Book> addBooks(List<Book> books, String username);
}
