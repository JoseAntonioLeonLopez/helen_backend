package com.helen.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helen.Repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
