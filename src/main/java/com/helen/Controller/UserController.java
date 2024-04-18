package com.helen.Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helen.Entity.User;
import com.helen.Service.CloudinaryService;
import com.helen.Service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	@Autowired
	private final CloudinaryService cloudinaryService;

	@Autowired
	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return userService.getUser(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserWithPublicationsByUsername(username)
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<?> addUser(
			@RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile,
			@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("firstSurname") String firstSurname,
			@RequestParam(value = "secondSurname", required = false) String secondSurname,
			@RequestParam("gender") int gender, @RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam("city") String city, @RequestParam("fkRole") Long fkRole) {
		try {
			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setFirstSurname(firstSurname);
			user.setSecondSurname(secondSurname);
			user.setGender(gender);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			user.setCity(city);
			user.setFkRole(fkRole);

			// Si se proporcionó un archivo de imagen, cargarlo
			if (multipartFile != null) {
				BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
				if (bi == null) {
					return new ResponseEntity<>("Usuario no válido: archivo no es una imagen", HttpStatus.BAD_REQUEST);
				}
				Map<String, String> result = cloudinaryService.upload(multipartFile);
				String imageUrl = result.get("url");
				String publicId = result.get("public_id");
				user.setImageUser(imageUrl);
				user.setPublicId(publicId);
			}

			return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al procesar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
		if (id == user.getIdUser()) {
			return userService.getUser(id)
					.map(userDB -> new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUser(@PathVariable("id") Long id) {
		Optional<User> userOptional = userService.getUser(id);
		if (userOptional.isEmpty()) {
			return new ResponseEntity<>("No existe el usuario", HttpStatus.NOT_FOUND);
		}

		User user = userOptional.get();
		String cloudinaryPublicationId = user.getPublicId();
		try {
			cloudinaryService.delete(cloudinaryPublicationId);
		} catch (IOException e) {
			return new ResponseEntity<>("Fallo al borrar la imagen del usuario", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userService.removeUser(id);
		return new ResponseEntity<>("Usuario borrado", HttpStatus.OK);
	}

	/*
	 * @GetMapping("/followers") public SomeData getMethodName(@RequestParam String
	 * param) { return new SomeData(); }
	 */

}
