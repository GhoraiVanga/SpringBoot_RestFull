package com.ghorai.api.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghorai.api.ui.model.request.UserDetailsModelRequest;
import com.ghorai.api.ui.model.response.UserDetailsModelResponse;
import com.ghorai.api.ui.service.UserService;
import com.ghorai.api.ui.shared.dto.userDto;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {
	
	 @Autowired
	 UserService userService;
	

		@GetMapping
		public String getUser()
		{
			return "get user was called";
		}
		
		@PostMapping
		public UserDetailsModelResponse createUser(@RequestBody UserDetailsModelRequest userdetails)
		{
			
			UserDetailsModelResponse userDetailsModelResponse = new UserDetailsModelResponse();
			userDto ud = new userDto();
			BeanUtils.copyProperties(userdetails, ud);
			userDto createUserDto=userService.createUser(ud);
			BeanUtils.copyProperties(createUserDto, userDetailsModelResponse);
			return userDetailsModelResponse ;
		}
		
		@PutMapping
		 public String updateUser()
	   {  
			
			return "User update succesfull";
			
	   }
	   @DeleteMapping 
		  public String deleteUsers()
		{
			return "delete users suceefull";
		}
}
