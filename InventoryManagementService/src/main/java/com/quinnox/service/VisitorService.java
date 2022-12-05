package com.quinnox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.quinnox.model.Server;
import com.quinnox.model.ServerOS;
import com.quinnox.model.Visitor;
import com.quinnox.repository.VisitorRepository;

@Service
public class VisitorService {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	MongoOperations mongoOperations;

	public Visitor saveVisitorInfo(Visitor visitor) {
		return visitorRepository.save(visitor);
	}

	public List<Visitor> getAll() {
		Query query=new Query();
		query.with(Sort.by(Sort.Direction.DESC, "loggedTime"));
		List<Visitor> visitor=mongoOperations.find(query, Visitor.class);
		return visitor;
	}

}
