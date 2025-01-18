package com.ghorai.api.ui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ghorai.api.ui.entity.EmpEntity;
import com.ghorai.api.ui.entity.SalaryEntity;
import com.ghorai.api.ui.shared.dto.empDto;
import com.ghorai.api.ui.shared.dto.userDto;


public interface UserService {
	
	userDto createUser(userDto user);
	
	// read operation
    List<EmpEntity> fetchDepartmentList();
    
    //read single element 
    
    EmpEntity fetchEmployee(String gender);
    Optional<EmpEntity> fetchEmployeeByID(Long id);
    
 // read operation
    List<EmpEntity> fetchspeceficYearBirthdateEmploye(int year);
    
    List<SalaryEntity> fetchSalary(Long emp_no);

}
