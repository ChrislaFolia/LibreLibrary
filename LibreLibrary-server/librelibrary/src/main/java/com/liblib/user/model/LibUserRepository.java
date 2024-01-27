package com.liblib.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibUserRepository  extends JpaRepository<LibUser, Integer> {

	@Query("from LibUser where phoneNumber = :phoneNumber")
	LibUser findLibUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
