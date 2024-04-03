package com.helen.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.helen.Entity.UserFav;
import com.helen.Service.UserFavService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/userFav")
@AllArgsConstructor
public class UserFavController {
	
	@Autowired
	private final UserFavService userFavService;
	
    @GetMapping
    public ResponseEntity<List<UserFav>> getAllUserFav() {
    	return new ResponseEntity<List<UserFav>>(userFavService.getAllUserFavs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFav> getUserFav(@PathVariable("id") Long id) {
		return userFavService.getUserFav(id)
				.map(userFav -> new ResponseEntity<>(userFav, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping
	public ResponseEntity<UserFav> addUserFav(@RequestBody UserFav userFav) {
		return new ResponseEntity<UserFav>(userFavService.addUserFav(userFav), HttpStatus.CREATED);
	}
    
    @PutMapping("/{id}")
	public ResponseEntity<UserFav> updateUserFav(@RequestBody UserFav userFav, @PathVariable("id") Long id) {
		if(id == userFav.getIdUserFav()) {
			return userFavService.getUserFav(id)
			    	.map(userFavDB -> new ResponseEntity<>(userFavService.updateUserFav(userFav), HttpStatus.OK))
			    	.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserFav> removeUserFav(@PathVariable("id") Long id) {
		if (userFavService.removeUserFav(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
