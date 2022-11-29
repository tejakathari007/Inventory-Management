package com.quinnox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.Visitor;

@Repository
public interface VisitorRepository extends MongoRepository<Visitor, String> {

}
