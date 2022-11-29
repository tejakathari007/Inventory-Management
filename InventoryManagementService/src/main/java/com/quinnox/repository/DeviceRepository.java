package com.quinnox.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

	public List<Device> findByServer(String id);

	public List<Device> findByClient(String id);

	public List<Device> findByStateAndStatus(String state,String status);

}
