package com.helen.Security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.helen.Entity.User;
import com.helen.Repository.UserRepository;
import com.helen.Service.CloudinaryService;

import io.jsonwebtoken.io.IOException;

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

	@Autowired
	private CloudinaryService cloudinaryService;

	public AuthenticationResponse register(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("firstSurname") String firstSurname,
			@RequestParam(value = "secondSurname", required = false) String secondSurname,
			@RequestParam("gender") int gender, @RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam("city") String city, @RequestParam(value = "fkRole", required = false) Long fkRole)
			throws IOException, java.io.IOException {
		try {
			String imageUrl = null;
			String publicId = null;

			if (multipartFile != null && !multipartFile.isEmpty()) {
				Map<String, String> cloudinaryResult = cloudinaryService.upload(multipartFile);
				imageUrl = cloudinaryResult.get("url");
				publicId = cloudinaryResult.get("public_id");
			}

			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setFirstSurname(firstSurname);
			user.setSecondSurname(secondSurname);
			user.setGender(gender);
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setPhoneNumber(phoneNumber);
			user.setImageUser(imageUrl);
			user.setPublicId(publicId);
			user.setCity(city);
			user.setFkRole(fkRole != null ? fkRole : 2L);

			user = userRepository.save(user);

			String token = jwtService.generateToken(user);

			return new AuthenticationResponse(token);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al cargar la imagen a Cloudinary", e);
		}
	}

	public AuthenticationResponse authenticate(User request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.generateToken(user);

		return new AuthenticationResponse(token);
	}
}
