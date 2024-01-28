package com.liblib.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.liblib.book.model.Book;
import com.liblib.book.model.BookRepository;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepo;
	
	@Override
	public List<Book> findAll() {
		return bookRepo.findAll();
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookRepo.findByIsbn(isbn);
	}

	@Override
	public Page<Book> findByPage(Integer pageNumber, Integer dataSize) {
		
		// 按照 isbn 降冪排序
		PageRequest pgb = PageRequest.of(pageNumber - 1, dataSize, Sort.Direction.DESC, "isbn");

		Page<Book> page = bookRepo.findAll(pgb);
		return page;
	}

}
