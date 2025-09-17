package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	   @Autowired
	   private  UserRepository userRepository;
	   
	   public UserResponse toResponse(User user) {
		      return UserResponse.builder()
		    		  .id(user.getId())
		    		  .name(user.getName())
		    		  .email(user.getEmail())
		    		  .build();
	           }
	   
	   
     @Override
	public UserResponse createUser(UserRequest request) {
		   User user = User.builder()
				  .name(request.getName())
				  .email(request.getEmail())
				  .password(request.getPassword())
				  .build();
		return toResponse(userRepository.save(user));
	}

	@Override
	public UserResponse getUserById(Long id) {
		
		return userRepository.findById(id)
				.map(this::toResponse)
				.orElseThrow(()-> new RuntimeException("User not found with id "+ id));
				
	}
	@Override
	public List<UserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll()
				.stream()
				.map(this::toResponse)
				 .collect(Collectors.toList());
	} 

	@Override
	public UserResponse updateUserById(Long id, UserRequest request) {
	    // Pehle dekhte hain user exist karta hai ya nahi
	    User existingUser = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id " + id));

	    // Ab existing user ke fields ko naye request data se update karte hain
	    existingUser.setName(request.getName());
	    existingUser.setEmail(request.getEmail());
	    existingUser.setPassword(request.getPassword()); // NOTE: production me hamesha password encrypt karna

	    // Save karke updated entity ko database me commit karenge
	    User updatedUser = userRepository.save(existingUser);

	    // Entity ko Response DTO me convert karenge
	    return toResponse(updatedUser);
	}


	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		 userRepository.deleteById(id);
		return "User Deleted Successfully" ;
	}




	   
	   
}
