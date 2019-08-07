package com.todoapp.backend.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.backend.app.model.UserModel;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.todoapp.backend.security.SecurityConstants.EXPIRATION_TIME;
import static com.todoapp.backend.security.SecurityConstants.HEADER_STRING;
import static com.todoapp.backend.security.SecurityConstants.SECRET;
import static com.todoapp.backend.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserModel creds = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		String role[] = new String[1];
		Collection<GrantedAuthority> list = ((User) auth.getPrincipal()).getAuthorities();
		list.forEach(it ->{
			role[0] = it.getAuthority().toString();
		});
		
		
		String token = JWT.create()
				.withClaim("name", ((User) auth.getPrincipal()).getUsername())
				.withClaim("role", role[0])
				.withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
		
		System.out.println("Token : " + token);
		response.addHeader("Access-Control-Expose-Headers", "Authorization"); // Front End kısmında Authorization bilgisine erişebilmek için ekliyoruz.
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        System.out.println("Response is: " + response.toString());
	}
}
