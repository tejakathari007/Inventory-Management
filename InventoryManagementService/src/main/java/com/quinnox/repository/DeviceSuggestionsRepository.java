package com.quinnox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.DeviceSuggestions;

@Repository
public interface DeviceSuggestionsRepository extends MongoRepository<DeviceSuggestions, String> {

}
