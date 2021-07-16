package com.tushar.lms.book.service;

import java.util.List;

import com.tushar.lms.book.dto.BookDto;
import com.tushar.lms.book.dto.IssuedBookDto;

public interface BookService {

	BookDto addNewBook(BookDto addNewBook);

	List<BookDto> getAllBooks();

	BookDto getBook(String bookId);

	List<IssuedBookDto> getIssuedBooks(String userId);

}
