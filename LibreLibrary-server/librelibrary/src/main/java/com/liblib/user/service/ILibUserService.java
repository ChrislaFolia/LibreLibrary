package com.liblib.user.service;

import com.liblib.user.model.LibUser;

public interface ILibUserService {

	boolean registerUser(LibUser userBean);
	
	LibUser checkLogin(LibUser userBean);
	
	void updateLastLoginTime(LibUser userBean);
	
	LibUser findLibUserByPhoneNumber(String phoneNumber);
	
	
}
