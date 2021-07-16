package com.tushar.lms.book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

	@Override
	public BookDto addNewBook(BookDto addNewBook) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book newBook = modelMapper.map(addNewBook, Book.class);
		newBook.setBookId(UUID.randomUUID().toString());
		Book savedBok = bookRepository.save(newBook);
		BookDto returnBook = modelMapper.map(savedBok, BookDto.class);
		return returnBook;
	}

	@Override
	public List<BookDto> getAllBooks() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class))
				.collect(Collectors.toList());
		return bookDtos;
	}

	@Override
	public BookDto getBook(String bookId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Book foundBook = bookRepository.findByBookId(bookId);
		BookDto bookDto = modelMapper.map(foundBook, BookDto.class);
		return bookDto;
	}

	@Override
	public List<IssuedBookDto> getIssuedBooks(String userId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Book> books = bookRepository.findByUserId(userId);
		List<IssuedBookDto> issuedBookDtos = books.stream().map(book -> modelMapper.map(book, IssuedBookDto.class))
				.collect(Collectors.toList());
		return issuedBookDtos;
	}

}
