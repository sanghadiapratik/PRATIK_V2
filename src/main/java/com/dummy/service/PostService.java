package com.dummy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.entity.Post;
import com.dummy.entity.UserPreview;



@Service
public class PostService {
	
	@Autowired
	com.dummy.repository.PostRepository postRepository;
	
	
	public void deleteUser(Post post) {
		postRepository.delete(post);
	}
	
	public List<Post> getPost() {
		return (List<Post>) postRepository.findAll();
	}
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	public Optional<Post> getPost(String postId) {
		return postRepository.findById(postId);
	}

	public List<Post> getPostByOwner(UserPreview userPreview) {
		
		return postRepository.findByOwner(userPreview);
	}
	
}
