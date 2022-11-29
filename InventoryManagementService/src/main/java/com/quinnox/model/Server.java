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
public class Server extends Auditable {

	@Id
	private String id;
	private String name;
	private String serialNo;
	private String ipAddress;
	private InstanceType instanceType;
	private State state;
	private MaintainanceType maintainanceType;
	private Date maintainanceStartDateTime;
	private Date maintainanceEndDateTime;
	private ServerOS os;
	private Region region;
	private HostingType hostingType;
	private List<ServerDevice> devices = new ArrayList<ServerDevice>();
	private List<ServerBrowser> browsers = new ArrayList<ServerBrowser>();

}
