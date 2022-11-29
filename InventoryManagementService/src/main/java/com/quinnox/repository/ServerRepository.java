package com.quinnox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.Server;

@Repository
public interface ServerRepository extends MongoRepository<Server, String> {

}
