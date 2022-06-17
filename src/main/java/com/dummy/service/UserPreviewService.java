package com.dummy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.entity.UserPreview;
import com.dummy.repository.UserPreviewRepository;

@Service
public class UserPreviewService {

	@Autowired
	UserPreviewRepository userPreviewRepository;
	
	public void deleteUser(UserPreview usePreview) {
		userPreviewRepository.delete(usePreview);
	}
	
	public List<UserPreview> getUserPreviews() {
		return (List<UserPreview>) userPreviewRepository.findAll();
	}
	public UserPreview save(UserPreview userPreview) {
		return userPreviewRepository.save(userPreview);
	}
	
	public Optional<UserPreview> getUserPreview(String id) {
		return userPreviewRepository.findById(id);
	}
	
	
}
