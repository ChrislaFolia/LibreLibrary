package com.liblib.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.liblib.user.model.LibUser;
import com.liblib.user.service.LibUserService;
import com.liblib.util.UntilJwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
	private UntilJwt utilJwt;

	@Autowired
	private LibUserService userService;

	// Define URLs to be excluded from JWT authentication
	private static final String[] excludedUrls = { "/liblib/login", "/liblib/libuser/register",
			"/liblib/book/.*" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestUrl = request.getRequestURI();

		// Check if the URL is in the exclusion list
		boolean isExcluded = false;
		for (String excludedUrl : excludedUrls) {
			if (requestUrl.matches(excludedUrl)) {
				isExcluded = true;
				break;
			}
		}
		if (isExcluded) {
			// If the URL is in the exclusion list, skip the filter and proceed to the next filter or controller
			filterChain.doFilter(request, response);
		}else {
			// Get request header value
			String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (bearerToken != null) {
				String phoneNumber = utilJwt.verifyToken(bearerToken);


				LibUser resultBean = userService.findLibUserByPhoneNumber(phoneNumber);
				if(resultBean!=null) {
					// forward request to controller or next filter
					filterChain.doFilter(request, response);            	
				}else {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.getWriter().write("Unauthorized");
				}

			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("Unauthorized");
			}
		}

	}


}
