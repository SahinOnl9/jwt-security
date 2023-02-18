package org.websec.jwtsecurity.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.websec.jwtsecurity.exception.InvalidBookException;
import org.websec.jwtsecurity.model.Book;

public final class BookValidator {

	private BookValidator() {
	};

	public static void validateBooks(List<Book> books) {

		Set<String> errSet = new HashSet<>();

		books.forEach(book -> validateBook(book, errSet));

		if (!errSet.isEmpty()) {
			throw new InvalidBookException(errSet);
		}
	}

	private static void validateBook(Book book, Set<String> errSet) {

		// Validate: Title
		if (book.getTitle() == null) {
			errSet.add("Book title can not be null.");
		} else if (book.getTitle().length() < 3) {
			errSet.add("Book title can not contain less than 3 characters.");
		}

		// Validate: Author
		if (book.getAuthor() == null) {
			errSet.add("Books author name can not be null.");
		} else if (book.getAuthor().length() < 3) {
			errSet.add("Book author name can not contain less than 3 characters.");
		}
	}
}
