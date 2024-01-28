package com.liblib.book.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class Book {

	@Id
	@Column(name = "isbn",nullable = false, columnDefinition = "varchar(20)")
    private String isbn;
	
	@Column(name = "name",nullable = false, columnDefinition = "nvarchar(60)")
	private String name;

	@Column(name = "author",nullable = false, columnDefinition = "nvarchar(30)")
    private String author;

	@Column(name = "introduction", columnDefinition = "nvarchar(255)")
    private String introduction;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private List<Inventory> inventory = new ArrayList<>();
}
