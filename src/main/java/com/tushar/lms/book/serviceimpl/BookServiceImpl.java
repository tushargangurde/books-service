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

import com.tushar.lms.book.dto.BookDto;
import com.tushar.lms.book.dto.IssuedBookDto;
import com.tushar.lms.book.entity.Book;
import com.tushar.lms.book.repository.BookRepository;
import com.tushar.lms.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public BookDto addNewBook(BookDto addNewBook) {
		logger.info("Inside BookServiceImpl ---------> addNewBook");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book newBook = modelMapper.map(addNewBook, Book.class);
		newBook.setBookId(UUID.randomUUID().toString());
		Book savedBok = bookRepository.save(newBook);
		BookDto returnBook = modelMapper.map(savedBok, BookDto.class);
		return returnBook;
	}

	@Override
	public List<BookDto> getAllBooks() {
		logger.info("Inside BookServiceImpl ---------> getAllBooks");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class))
				.collect(Collectors.toList());
		return bookDtos;
	}

	@Override
	public BookDto getBook(String bookId) {
		logger.info("Inside BookServiceImpl ---------> getBook");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book foundBook = bookRepository.findByBookId(bookId);
		BookDto bookDto = modelMapper.map(foundBook, BookDto.class);
		return bookDto;
	}

	@Override
	public List<IssuedBookDto> getIssuedBooks(String userId) {
		logger.info("Inside BookServiceImpl ---------> getIssuedBooks");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findByUserId(userId);
		List<IssuedBookDto> issuedBookDtos = books.stream().map(book -> modelMapper.map(book, IssuedBookDto.class))
				.collect(Collectors.toList());
		return issuedBookDtos;
	}

}
