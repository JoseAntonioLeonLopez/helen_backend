package com.helen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.Comment;
import com.helen.Repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private final CommentRepository commentRepository;
	
	public List<Comment> getAllComments() {
		return this.commentRepository.findAll();
	}
	
	public Optional<Comment> getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void removeComment(Long id) {
    	commentRepository.deleteById(id);
    }
	
}
