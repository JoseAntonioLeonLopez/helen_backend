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

import com.helen.Entity.UserFollow;
import com.helen.Service.UserFollowService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usersFollow")
@AllArgsConstructor
public class UserFollowController {
	
	@Autowired
	private final UserFollowService userFollowService;
	
    @GetMapping
    public ResponseEntity<List<UserFollow>> getAllUsersFollow() {
    	return new ResponseEntity<List<UserFollow>>(userFollowService.getAllUsersFollow(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFollow> getUserFollow(@PathVariable("id") Long id) {
		return userFollowService.getUserFollow(id)
				.map(userFollow -> new ResponseEntity<>(userFollow, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping
	public ResponseEntity<UserFollow> addUserFollow(@RequestBody UserFollow userFollow) {
		return new ResponseEntity<UserFollow>(userFollowService.addUserFollow(userFollow), HttpStatus.CREATED);
	}
    
    @PutMapping("/{id}")
	public ResponseEntity<UserFollow> updateUserFollow(@RequestBody UserFollow userFollow, @PathVariable("id") Long id) {
		if(id == userFollow.getIdUserFollow()) {
			return userFollowService.getUserFollow(id)
			    	.map(userFollowDB -> new ResponseEntity<>(userFollowService.updateUserFollow(userFollow), HttpStatus.OK))
			    	.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserFollow> removeUserFollow(@PathVariable("id") Long id) {
		if (userFollowService.removeUserFollow(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
