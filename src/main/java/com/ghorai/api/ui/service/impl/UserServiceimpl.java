package com.ghorai.api.ui.service.impl;

import java.lang.System.Logger;
import java.net.http.HttpClient;
import java.util.Iterator;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghorai.api.ui.entity.UserEntity;
import com.ghorai.api.ui.repositry.UserRepository;
import com.ghorai.api.ui.service.UserService;
import com.ghorai.api.ui.shared.dto.userDto;

import ch.qos.logback.classic.pattern.Util;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public userDto createUser(userDto user) {
		// TODO Auto-generated method stub
		
		 UserEntity uEntity =new UserEntity();
		 UserEntity rUserEntity = null;
		 BeanUtils.copyProperties(user, uEntity);
		 
		 //Another Process of Find Duplicate email Exist or not 
		 if(userRepository.findByEmail(user.getEmail()) !=null ) throw new RuntimeException("User Alredy Exists into database ");
		 
		 
		 Iterator<UserEntity> luserIterable= userRepository.findAll().iterator();
		 int Count =0 ;
		  while (luserIterable.hasNext()) {
			UserEntity userEntity = (UserEntity) luserIterable.next();
			Count++ ;
			if(userEntity.getEmail().equals(user.getEmail()))
			{
				System.out.println("Find Out");
			}
			else {
				uEntity.setUserId("Dulicate Data " + Count);
				rUserEntity  = userRepository.save(uEntity); // UserRepository data save  only one time if entity equal all time 
				System.out.println("Data Saved ");
			}
			
		}
		 
		 userDto uDto =new userDto();
		 BeanUtils.copyProperties(rUserEntity, uDto);
		 return uDto;
	}

}
