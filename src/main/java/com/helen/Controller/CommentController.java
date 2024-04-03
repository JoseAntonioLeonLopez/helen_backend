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

import com.helen.Entity.Comment;
import com.helen.Service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
	
	@Autowired
	private final CommentService commentService;
	
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComment() {
    	return new ResponseEntity<List<Comment>>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
		return commentService.getComment(id)
				.map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		return new ResponseEntity<Comment>(commentService.addComment(comment), HttpStatus.CREATED);
	}
    
    @PutMapping("/{id}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("id") Long id) {
		if(id == comment.getIdComment()) {
			return commentService.getComment(id)
			    	.map(commentDB -> new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK))
			    	.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Comment> removeComment(@PathVariable("id") Long id) {
		if (commentService.removeComment(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
