package com.quinnox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document
@NoArgsConstructor
public class Client extends Auditable{
	@Id
	private String id;
	private String name;
	private String engagement;
	private Date contractStartDate;
	private Date contractEndDate;
	private String email;
	private List<ClientDevice> devices=new ArrayList<ClientDevice>();
	private List<ClientBrowser> browsers=new ArrayList<ClientBrowser>();
}
