package com.ghorai.api.ui.service;

import org.springframework.stereotype.Service;

import com.ghorai.api.ui.shared.dto.userDto;


public interface UserService {
	
	userDto createUser(userDto user);

}
