package com.liblib.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liblib.book.model.BorrowingRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LibUser")
public class LibUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "phoneNumber",nullable = false, columnDefinition = "varchar(25)")
	private String phoneNumber;

	@Column(name = "password",nullable = false, columnDefinition = "varchar(255)")
	private String password;
	
	@Column(name = "userName",nullable = false, columnDefinition = "nvarchar(30)")
	private String userName;

	@Column(name = "registrationTime",nullable = false, columnDefinition = "datetime2")
	private Date registrationTime;

	@Column(name = "lastLoginTime", columnDefinition = "datetime2")
	private Date lastLoginTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "libUser")
	private List<BorrowingRecord> borrowingRecord = new ArrayList<>();
	
}
