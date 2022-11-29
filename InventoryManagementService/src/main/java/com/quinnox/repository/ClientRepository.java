package com.quinnox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
