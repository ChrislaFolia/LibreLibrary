package com.liblib.book.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>  {

	@Query("from Inventory where isbn = :isbn")
	List<Inventory> findByIsbn(@Param("isbn") String isbn);


	
	
}
