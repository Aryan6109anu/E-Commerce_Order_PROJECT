package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

public interface UserService {
	
	  UserResponse createUser(UserRequest request);
	  UserResponse getUserById(Long id);
	  List<UserResponse> getAllUsers();
	  UserResponse  updateUserById(Long id, UserRequest request);
	  String  deleteUser(Long id);
       
}
