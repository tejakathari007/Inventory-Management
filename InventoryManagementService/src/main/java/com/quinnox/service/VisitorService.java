package com.quinnox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.model.Visitor;
import com.quinnox.repository.VisitorRepository;

@Service
public class VisitorService {

	@Autowired
	private VisitorRepository visitorRepository;

	public Visitor saveVisitorInfo(Visitor visitor) {
		return visitorRepository.save(visitor);
	}
	
	public List<Visitor> getAll() {
		return visitorRepository.findAll();
	}

}
