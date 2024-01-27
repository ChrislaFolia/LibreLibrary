package com.liblib.user.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liblib.user.model.LibUser;
import com.liblib.user.model.ResponseJwt;
import com.liblib.user.service.ILibUserService;

@RestController
@RequestMapping("/login")
@CrossOrigin()
public class LoginController {

	@Autowired
	private ILibUserService userService;
	
	@PostMapping()
	public String login(@RequestBody LibUser userBean) {
		try {
			ResponseJwt loginResponse = new ResponseJwt();
			
			LibUser resultBean = userService.checkLogin(userBean); // 驗證帳號密碼
			userService.updateLastLoginTime(userBean);
			if (resultBean != null) {
				String token = "";
				try {
					token = generateToken(resultBean.getPhoneNumber()); // 生成token其中夾帶使用者帳號
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
			ResponseJwt loginResponse = new ResponseJwt();
			loginResponse.setStatus(false);
			loginResponse.setToken("");
			loginResponse.setUserName("");
			
			return loginResponse.toJSONString();
			
		}
		
		
	}
	
	String SECRET_KEY = "nO,4o*Czn|9Tj8P[.j,JT%tFY=|=A{";
	Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

	String generateToken(String username) throws Exception {
		String token = "";
		LocalDateTime dateTime = LocalDateTime.now().plusMinutes(60);
		Date expireTime = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		token = JWT.create().withClaim("username", username).withExpiresAt(expireTime).sign(algorithm);

		return token;
	}

	String verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).build();
		String username = "";

		DecodedJWT decodedJWT = verifier.verify(token);
		username = decodedJWT.getClaim("username").asString();

		return username;
	}
	
}
