package com.helen.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helen.Entity.User;
import com.helen.Security.AuthenticationResponse;
import com.helen.Security.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<AuthenticationResponse> register(
	        @RequestParam("file") MultipartFile multipartFile,
	        @RequestParam("username") String username,
	        @RequestParam("name") String name,
	        @RequestParam("firstSurname") String firstSurname,
	        @RequestParam(value = "secondSurname", required = false) String secondSurname,
	        @RequestParam("gender") int gender,
	        @RequestParam("email") String email,
	        @RequestParam("password") String password,
	        @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
	        @RequestParam("city") String city,
	        @RequestParam(value = "fkRole", required = false) Long fkRole) throws IOException {
	    AuthenticationResponse response = authService.register(
	            multipartFile,
	            username,
	            name,
	            firstSurname,
	            secondSurname,
	            gender,
	            email,
	            password,
	            phoneNumber,
	            city,
	            fkRole);
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
