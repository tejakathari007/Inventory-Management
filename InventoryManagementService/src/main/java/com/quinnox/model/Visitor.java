package com.quinnox.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Visitor {

	@JsonIgnore
	@Id
	private String id;
	private String user;
	private String role;
	private String ip;
	private String method;
	private String url;
	private String page;
	private String queryString;
	private String refererPage;
	private String userAgent;
	private Long loggedTime;
//	private boolean uniqueVisit;

}
