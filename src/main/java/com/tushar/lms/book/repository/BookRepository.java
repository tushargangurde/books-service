package com.tushar.lms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.lms.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByBookId(String bookId);

	List<Book> findByUserId(String userId);

}
