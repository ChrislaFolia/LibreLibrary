package com.liblib.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.user.model.LibUser;
import com.liblib.user.service.ILibUserService;

@RestController
@RequestMapping("/libuser")
@CrossOrigin()
public class LibUserController {

	@Autowired
	private ILibUserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody LibUser userBean) {
		boolean result = userService.registerUser(userBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
