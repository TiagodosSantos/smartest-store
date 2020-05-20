package com.smartest.store.conf.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartest.store.model.User;
import com.smartest.store.repository.UserRepository;

@Service
public class AutenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = repository.findByEmail(username);
		if (user.isPresent()) 
			return user.get();
		
		throw new UsernameNotFoundException("User not found!");
	}

}
