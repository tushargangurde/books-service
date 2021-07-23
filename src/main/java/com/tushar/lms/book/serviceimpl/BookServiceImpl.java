package com.tushar.lms.book.serviceimpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.lms.book.entity.Book;
import com.tushar.lms.book.repository.BookRepository;
import com.tushar.lms.book.requestmodel.NewBookRequest;
import com.tushar.lms.book.responsemodel.AllBooksListResponse;
import com.tushar.lms.book.responsemodel.GetBookResponse;
import com.tushar.lms.book.responsemodel.IssuedBookResponse;
import com.tushar.lms.book.responsemodel.NewBookResponse;
import com.tushar.lms.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public NewBookResponse addNewBook(NewBookRequest addNewBook) {
		logger.info("Inside BookServiceImpl ---------> addNewBook");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book newBook = modelMapper.map(addNewBook, Book.class);
		newBook.setBookId(UUID.randomUUID().toString());
		Book savedBok = bookRepository.save(newBook);
		NewBookResponse returnBook = modelMapper.map(savedBok, NewBookResponse.class);
		return returnBook;
	}

	@Override
	public List<AllBooksListResponse> getAllBooks() {
		logger.info("Inside BookServiceImpl ---------> getAllBooks");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findAll();
		List<AllBooksListResponse> bookDtos = books.stream()
				.map(book -> modelMapper.map(book, AllBooksListResponse.class)).collect(Collectors.toList());
		return bookDtos;
	}

	@Override
	public GetBookResponse getBook(String bookId) {
		logger.info("Inside BookServiceImpl ---------> getBook");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book foundBook = bookRepository.findByBookId(bookId);
		GetBookResponse bookDto = modelMapper.map(foundBook, GetBookResponse.class);
		return bookDto;
	}

	@Override
	public List<IssuedBookResponse> getIssuedBooks(String userId) {
		logger.info("Inside BookServiceImpl ---------> getIssuedBooks");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findByUserId(userId);
		List<IssuedBookResponse> issuedBookDtos = books.stream().map(book -> modelMapper.map(book, IssuedBookResponse.class))
				.collect(Collectors.toList());
		return issuedBookDtos;
	}

}
