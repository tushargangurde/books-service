package com.tushar.lms.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.lms.book.requestmodel.NewBookRequest;
import com.tushar.lms.book.responsemodel.AllBooksListResponse;
import com.tushar.lms.book.responsemodel.GetBookResponse;
import com.tushar.lms.book.responsemodel.IssuedBookResponse;
import com.tushar.lms.book.responsemodel.NewBookResponse;
import com.tushar.lms.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	Logger logger = LoggerFactory.getLogger(BookController.class);

	@PostMapping("/add")
	public ResponseEntity<NewBookResponse> addNewBook(@RequestBody NewBookRequest addNewBook) {
		logger.info("Inside BookController ---------> addNewBook");
		NewBookResponse newBook = bookService.addNewBook(addNewBook);
		return new ResponseEntity<NewBookResponse>(newBook, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AllBooksListResponse>> getAllBooks() {
		logger.info("Inside BookController ---------> getAllBooks");
		List<AllBooksListResponse> bookList = bookService.getAllBooks();
		return new ResponseEntity<List<AllBooksListResponse>>(bookList, HttpStatus.FOUND);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<GetBookResponse> getBook(@PathVariable String bookId) {
		logger.info("Inside BookController ---------> getBook");
		GetBookResponse book = bookService.getBook(bookId);
		return new ResponseEntity<GetBookResponse>(book, HttpStatus.FOUND);
	}

	@GetMapping("/issuedBooks/{userId}")
	public ResponseEntity<List<IssuedBookResponse>> getIssuedBooks(@PathVariable String userId) {
		logger.info("Inside BookController ---------> getIssuedBooks");
		List<IssuedBookResponse> issuedBookDtos = bookService.getIssuedBooks(userId);
		return new ResponseEntity<List<IssuedBookResponse>>(issuedBookDtos, HttpStatus.OK);
	}

}
