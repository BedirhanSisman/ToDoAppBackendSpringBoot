package com.todoapp.backend.security.filter;

import static com.todoapp.backend.security.SecurityConstants.HEADER_STRING;
import static com.todoapp.backend.security.SecurityConstants.SECRET;
import static com.todoapp.backend.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			// parse the token.
			Map<String, Claim> claimMaps = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getClaims();
			
			String name = claimMaps.get("name").asString();
			String role = claimMaps.get("role").asString();

			if (name != null && role != null) {
		        final List<SimpleGrantedAuthority> authorities = new LinkedList<SimpleGrantedAuthority>();
		        authorities.add(new SimpleGrantedAuthority(role));
				return new UsernamePasswordAuthenticationToken(name, null, authorities);
			}
			
			return null;
		}
		
		return null;
	}
}
