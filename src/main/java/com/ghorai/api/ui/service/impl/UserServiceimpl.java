package com.ghorai.api.ui.service.impl;

import java.lang.System.Logger;
import java.net.http.HttpClient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.ghorai.api.ui.entity.EmpEntity;
import com.ghorai.api.ui.entity.SalaryEntity;
import com.ghorai.api.ui.entity.UserEntity;
import com.ghorai.api.ui.repositry.EmployeeRepositroy;
import com.ghorai.api.ui.repositry.SalaryRepository;
import com.ghorai.api.ui.repositry.UserRepository;
import com.ghorai.api.ui.service.UserService;
import com.ghorai.api.ui.shared.dto.empDto;
import com.ghorai.api.ui.shared.dto.userDto;
import com.ghorai.api.ui.shared.util.Utils;


import ch.qos.logback.classic.pattern.Util;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmployeeRepositroy employeeRepositroy;
	@Autowired
	SalaryRepository salaryRepository;
	
	@Autowired
	 Utils  utils;
	
	

	@Override
	public userDto createUser(userDto user) {
		// TODO Auto-generated method stub
		
		 UserEntity uEntity =new UserEntity();
		 UserEntity rUserEntity = null;
		 BeanUtils.copyProperties(user, uEntity);
		 
		 //Another Process of Find Duplicate email Exist or not chrck 
		 if(userRepository.findByEmail(user.getEmail()) !=null ) throw new RuntimeException("User Alredy Exists into database ");
		 
		    uEntity.setUserId("Dulicate Data " + 2);
			//uEntity.setEncriptedPassword(utils.getPassword(10));
		    // uEntity.setEncriptedPassword(bcryptPasswordEncoder.encode(user.getPassword()));
			rUserEntity  = userRepository.save(uEntity);
		 
		 
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
				uEntity.setEncriptedPassword(utils.getPassword(10));
				rUserEntity  = userRepository.save(uEntity); // UserRepository data save  only one time if entity equal all time 
				System.out.println("Data Saved ");
			}
		  }
		 
		  userDto uDto =new userDto();
		 BeanUtils.copyProperties(rUserEntity, uDto);
		 return uDto;
	}



	public List<EmpEntity> fetchDepartmentList() {
		// TODO Auto-generated method stub
		
		return (List<EmpEntity>) employeeRepositroy.findAll();
		
	}



	@Override
	public EmpEntity fetchEmployee(String gender) {
		// TODO Auto-generated method stub
		
		EmpEntity empEntity=	employeeRepositroy.findBygender(gender);
		
		
		return empEntity;
	}



	@Override
	public Optional<EmpEntity> fetchEmployeeByID(Long id) {
		
		Optional<EmpEntity> empEntity=	employeeRepositroy.findById(id);
		
		return empEntity;
	}
	
	@Override
	public List<EmpEntity> fetchspeceficYearBirthdateEmploye(int year) {
		// TODO Auto-generated method stub
	int yearToFilter=year;
	DateTimeFormatter formate=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	List<EmpEntity>	listEmpEn =   employeeRepositroy
			                      .findAll()
			                      .stream()
			                      .filter(d -> LocalDate.parse(d.getBirth_date(),formate).getYear()==yearToFilter)
			                      .toList();
	         return listEmpEn;
	}



	@Override
	public List<SalaryEntity> fetchSalary(Long emp_no) {

		List<SalaryEntity> listsaSalaryEntities =   salaryRepository.findByEmpNo(emp_no);
		return listsaSalaryEntities;
		
		
		
	}



	



	

}
