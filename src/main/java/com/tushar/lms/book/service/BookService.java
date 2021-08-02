package com.tushar.lms.book.service;

import java.util.List;

import com.tushar.lms.book.requestmodel.NewBookRequest;
import com.tushar.lms.book.responsemodel.AllBooksListResponse;
import com.tushar.lms.book.responsemodel.GetBookResponse;
import com.tushar.lms.book.responsemodel.IssuedBookResponse;
import com.tushar.lms.book.responsemodel.NewBookResponse;

public interface BookService {

	NewBookResponse addNewBook(NewBookRequest addNewBook);

	List<AllBooksListResponse> getAllBooks();

	GetBookResponse getBook(String bookId);

	List<IssuedBookResponse> getIssuedBooks(String userId);

	List<AllBooksListResponse> getAvailableBooks();

}
