package com.liblib.book.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("from Book where isbn = :isbn")
	Book findByIsbn(@Param("isbn") String isbn);
}
