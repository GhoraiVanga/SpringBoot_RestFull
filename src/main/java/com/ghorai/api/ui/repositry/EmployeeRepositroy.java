package com.ghorai.api.ui.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ghorai.api.ui.entity.EmpEntity;
import com.ghorai.api.ui.shared.dto.empDto;

@Repository
public interface EmployeeRepositroy extends JpaRepository<EmpEntity,Long> {
	
	EmpEntity findBygender(String gender);
	

}
