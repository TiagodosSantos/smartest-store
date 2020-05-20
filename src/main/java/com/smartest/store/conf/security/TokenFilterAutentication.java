package com.smartest.store.conf.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smartest.store.dto.TokenDto;
import com.smartest.store.model.User;
import com.smartest.store.repository.UserRepository;

public class TokenFilterAutentication extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository repository;

	public TokenFilterAutentication(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		if (tokenService.isValidToken(token)) 
			authenticateUser(token);
		
		filterChain.doFilter(request, response);
	}

	private void authenticateUser(String token) {
		Long userId = tokenService.getUserId(token);
		User user = repository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith(TokenDto.Type.BEARER.getTypeName()+" ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
