package com.ghorai.api.ui.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ghorai.api.ui.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
    UserEntity findByEmail(String email);
	
}
