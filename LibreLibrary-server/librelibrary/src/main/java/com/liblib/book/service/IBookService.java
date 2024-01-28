package com.liblib.book.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.liblib.book.model.Book;

public interface IBookService {

	List<Book> findAll();
	
	Book findByIsbn(String isbn);

	Page<Book> findByPage(Integer pageNumber, Integer dataSize);
}
