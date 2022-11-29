package com.quinnox.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByUserName(String userName);
	
}