package com.ghorai.api.ui.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ghorai.api.ui.entity.SalaryEntity;

@EnableJpaRepositories
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {

	 List<SalaryEntity> findByEmpNo(Long empNo);
}
