package com.dummy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.entity.Comment;
import com.dummy.entity.UserPreview;

@Service
public class CommentService {
	
	@Autowired
	com.dummy.repository.CommentRepository  CommentRepository;
	
	public void delete(Comment comment) {
		CommentRepository.delete(comment);
	}
	
	public List<Comment> getComment() {
		return (List<Comment>) CommentRepository.findAll();
	}
	public Comment save(Comment comment) {
		return CommentRepository.save(comment);
	}
	
	public Optional<Comment> getComment(String id) {
		return CommentRepository.findById(id);
	}

	public List<Comment> getPostByOwner(UserPreview owner) {
		
		return CommentRepository.findByOwner(owner);
	}
	
}
