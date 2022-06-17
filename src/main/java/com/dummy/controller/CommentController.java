package com.dummy.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.dao.CommentCreate;
import com.dummy.entity.Comment;
import com.dummy.entity.UserPreview;
import com.dummy.service.CommentService;
import com.dummy.service.UserPreviewService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserPreviewService userPreviewService;

	@PostMapping
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentCreate commentCreate) {
         
		System.out.println(commentCreate.getMessage());
		
		try {
			Optional<UserPreview> userPreviewOptioanl=userPreviewService.getUserPreview(commentCreate.getOwner());
			UserPreview userPreview=userPreviewOptioanl.get();
			
			Comment comment=new Comment();
			comment.setMessage(commentCreate.getMessage());
			comment.setOwner(userPreview);
			comment.setPost(commentCreate.getPost());
			comment.setPublishDate(new java.util.Date().toString());
			
			commentService.save(comment);
	         
	        return new ResponseEntity<>(comment,HttpStatus.CREATED);
			
		}catch (Exception e) {
			 return new ResponseEntity<>(new RuntimeException(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@RequestBody CommentCreate commentCreate,@PathVariable String commentId) {
		
		try {
			Optional<UserPreview> userPreviewOptioanl=userPreviewService.getUserPreview(commentCreate.getOwner());
			UserPreview userPreview=userPreviewOptioanl.get();
			
			Comment comment=new Comment();
			comment.setId(commentId);
			comment.setMessage(commentCreate.getMessage());
			comment.setOwner(userPreview);
			comment.setPost(commentCreate.getPost());
			comment.setPublishDate(new java.util.Date().toString());
			
			commentService.save(comment);
	         
	        return new ResponseEntity<>(comment,HttpStatus.CREATED);
			
		}catch (Exception e) {
			 return new ResponseEntity<>(new RuntimeException(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping("/getComment/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable String commentId) {
		
		try {
			Optional<Comment> comment=commentService.getComment(commentId);
			
	        return new ResponseEntity<Comment>(comment.get(),HttpStatus.OK);
		}catch (Exception e) {
			  return new ResponseEntity<Exception>(new RuntimeException(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
    }
	
}
