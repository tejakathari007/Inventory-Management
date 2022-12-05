package com.quinnox.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document
@NoArgsConstructor	
public class OtherDevices extends Auditable{

	@Id
	private String id;
	private String name;
	private String uniqueId;
	private String type;
}
