package com.tushar.lms.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.lms.book.dto.BookDto;
import com.tushar.lms.book.dto.IssuedBookDto;
import com.tushar.lms.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto addNewBook) {
		BookDto newBook = bookService.addNewBook(addNewBook);
		return new ResponseEntity<BookDto>(newBook, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<BookDto> bookList = bookService.getAllBooks();
		return new ResponseEntity<List<BookDto>>(bookList, HttpStatus.FOUND);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto> getBook(@PathVariable String bookId) {
		BookDto book = bookService.getBook(bookId);
		return new ResponseEntity<BookDto>(book, HttpStatus.FOUND);
	}

	@GetMapping("/issuedBooks/{userId}")
	public ResponseEntity<List<IssuedBookDto>> getIssuedBooks(@PathVariable String userId) {
		List<IssuedBookDto> issuedBookDtos = bookService.getIssuedBooks(userId);
		return new ResponseEntity<List<IssuedBookDto>>(issuedBookDtos, HttpStatus.OK);
	}

}
