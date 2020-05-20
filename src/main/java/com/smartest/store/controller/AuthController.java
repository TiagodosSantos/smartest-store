package com.smartest.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartest.store.conf.security.TokenService;
import com.smartest.store.controller.form.LoginForm;
import com.smartest.store.dto.TokenDto;
import com.smartest.store.model.Customer;



/**
 * 
 * Controller responsible for user authentication
 * 
 * @author Tiago Santos
 * @since   2020-03-19
 * 
 */


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	/**
	   * This is  method returns the Token of the authenticated user
	   * 
	   * @param loginForm form that contains user email and password
	   * @return TokenDto object that contains the type and token
	   * @exception Exception on AuthenticationException and general errors.
	   * @see TokenDto LoginForm
	   */
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm) {
		try {
			Authentication authentication = authManager.authenticate(loginForm.converter());
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, TokenDto.Type.BEARER));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
