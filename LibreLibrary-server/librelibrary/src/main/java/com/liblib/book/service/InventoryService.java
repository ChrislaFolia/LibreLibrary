package com.liblib.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liblib.book.model.Inventory;
import com.liblib.book.model.InventoryRepository;

@Service
public class InventoryService implements IInventoryService {

	@Autowired
	private InventoryRepository iRepo;
	
	@Override
	public List<Inventory> findAll() {
		return iRepo.findAll();
	}

	@Override
	public List<Inventory> findByIsbn(String isbn) {
		return iRepo.findByIsbn(isbn);
	}

	@Override
	public List<Inventory> findByIds(List<Integer> inventoryIds) {
		return iRepo.findAllById(inventoryIds);
	}


}
