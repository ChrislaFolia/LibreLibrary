package com.liblib.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class UntilJwt {

	
	private static final String SECRET_KEY = "nO,4o*Czn|9Tj8P[.j,JT%tFY=|=A{";
	Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String phoneNumber) throws Exception {
		String token = "";
		LocalDateTime dateTime = LocalDateTime.now().plusMinutes(60);
		Date expireTime = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		token = JWT.create().withClaim("phoneNumber", phoneNumber).withExpiresAt(expireTime).sign(algorithm);

		return token;
	}

	public String verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).build();
		String phoneNumber = "";

		DecodedJWT decodedJWT = verifier.verify(token);
		phoneNumber = decodedJWT.getClaim("phoneNumber").asString();

		return phoneNumber;
	}
}
