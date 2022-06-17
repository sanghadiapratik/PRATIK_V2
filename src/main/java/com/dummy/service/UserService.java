package com.dummy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.entity.Users;
import com.dummy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void deleteUser(Users User) {
		userRepository.delete(User);
	}
	
	public List<Users> getUsers() {
		return (List<Users>) userRepository.findAll();
	}
	public Users save(Users User) {
		return userRepository.save(User);
	}
	
	public Optional<Users> getUser(String UserId) {
		return userRepository.findById(UserId);
	}
	
	
	
}
