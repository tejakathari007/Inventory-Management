package com.quinnox.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quinnox.model.Browser;
import com.quinnox.model.Device;

@Repository
public interface BrowserRepository extends MongoRepository<Browser, String> {
	public List<Browser> findByServer(String id);
	public List<Browser> findByClient(String id);
	public List<Browser> findByBrowserStatus(String status);

}
