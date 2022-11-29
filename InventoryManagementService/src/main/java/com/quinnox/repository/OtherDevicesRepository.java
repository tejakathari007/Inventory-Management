package com.quinnox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.OtherDevices;
import com.quinnox.model.Visitor;

@Repository
public interface OtherDevicesRepository extends MongoRepository<OtherDevices, String> {

}
