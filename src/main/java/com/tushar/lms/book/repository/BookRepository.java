package com.tushar.lms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tushar.lms.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByBookId(String bookId);

	List<Book> findByUserId(String userId);

	@Query(value = "{call book_getAvailableBooks}", nativeQuery = true)
	List<Book> getAvailableBooks();

}
