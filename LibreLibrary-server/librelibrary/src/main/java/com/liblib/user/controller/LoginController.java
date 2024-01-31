package com.liblib.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
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
					loginResponse.setUserId(resultBean.getUserId());
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
			loginResponse.setUserId(-1);
			
			return loginResponse.toJSONString();
		}
	}
	
	@PostMapping("/auth")
	public String auth(@RequestBody Map<String, String> request) {
		LoginResponse response = new LoginResponse();
		String token = request.get("token");
		
		try {
			String phoneNumber = utilJwt.verifyToken(token);
			String newToken = utilJwt.generateToken(phoneNumber);
			
			response.setStatus(true);
			response.setPhoneNumber(phoneNumber);
			response.setToken(newToken);

		} catch (JWTVerificationException exception) {
			System.out.println("jwt verify fail");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println(response.toJSONString());

		return response.toJSONString();
	}
	
	
}
