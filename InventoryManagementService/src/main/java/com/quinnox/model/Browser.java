package com.quinnox.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document
@NoArgsConstructor
public class Browser extends Auditable {
	@Id
	private String id;
	private String name;
	private String version;
	@DBRef
	private Client client;
	@DBRef
	private Server server;
	private Date clientSubscriptionStartsOn;
	private Date clientSubscriptionEndsOn;
	private BrowserStatus browserStatus;

}
