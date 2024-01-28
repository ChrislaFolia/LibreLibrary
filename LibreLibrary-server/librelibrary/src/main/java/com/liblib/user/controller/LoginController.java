package com.liblib.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.user.model.LibUser;
import com.liblib.user.model.LoginResponse;
import com.liblib.user.service.ILibUserService;
import com.liblib.util.UntilJwt;

@RestController
@RequestMapping("/login")
@CrossOrigin()
public class LoginController {

	@Autowired
	private ILibUserService userService;
	
	@Autowired
	private UntilJwt utilJwt;
	
	@PostMapping()
	public String login(@RequestBody LibUser userBean) {
		try {
			LoginResponse loginResponse = new LoginResponse();
			
			LibUser resultBean = userService.checkLogin(userBean); // 驗證帳號密碼
			userService.updateLastLoginTime(userBean);
			if (resultBean != null) {
				String token = "";
				try {
					token = utilJwt.generateToken(resultBean.getPhoneNumber()); // 生成token其中夾帶使用者帳號
					loginResponse.setStatus(true);
					loginResponse.setToken(token);
					loginResponse.setUserName(resultBean.getUserName());
					loginResponse.setPhoneNumber(resultBean.getPhoneNumber());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			return loginResponse.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setStatus(false);
			loginResponse.setToken("");
			loginResponse.setUserName("");
			
			return loginResponse.toJSONString();
			
		}
		
		
	}
	
	
	
}
