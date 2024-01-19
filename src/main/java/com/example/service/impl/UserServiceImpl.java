package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		if (emailExists(user.getEmail())) {
             throw new RuntimeException("このメールアドレスはすでに登録されています。");
        }
		return userRepository.save(user);
	}
	
	public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
	
	@Override
	public User getUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		return optionalUser.get();
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User updateUser(User user) {
		if (emailExistsAndNotSameUser(user.getEmail(), user.getId())) {
			throw new RuntimeException("このメールアドレスはすでに登録されています。");
		}
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}
	
	private boolean emailExistsAndNotSameUser(String email, Long userId) {
		return userRepository.findByEmail(email)
				.map(existingUser -> !existingUser.getId().equals(userId))
				.orElse(false);
	}
	
	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
}
