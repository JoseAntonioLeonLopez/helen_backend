package com.helen.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helen.Entity.User;
import com.helen.Repository.UserRepository;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User request) {
		User user = new User();
		user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setFirstSurname(request.getFirstSurname());
        user.setSecondSurname(request.getSecondSurname());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); 
        user.setPhoneNumber(request.getPhoneNumber());
        user.setImageUser(request.getImageUser());
        user.setPublicId(request.getPublicId());
        user.setCity(request.getCity());
        
        if (request.getFkRole() == null) {
            user.setFkRole((long)2);
        } else {
            user.setFkRole(request.getFkRole());
        }
        
        user = userRepository.save(user);
        
        String token = jwtService.generateToken(user);
        
        return new AuthenticationResponse(token);
	}



    public AuthenticationResponse authenticate(User request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}


