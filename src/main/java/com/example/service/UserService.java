package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {
	User createUser(User user);
	
	User getUserById(Long userId);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
