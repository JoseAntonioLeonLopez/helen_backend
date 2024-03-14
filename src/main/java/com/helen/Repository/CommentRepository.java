package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
