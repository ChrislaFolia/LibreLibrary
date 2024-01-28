package com.liblib.book.service;

import java.util.List;

import com.liblib.book.model.Inventory;

public interface IInventoryService {

	List<Inventory> findAll();
	
	List<Inventory> findByIsbn(String isbn);
}
