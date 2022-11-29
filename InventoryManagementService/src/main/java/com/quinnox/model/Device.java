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
public class Device extends Auditable {
	@Id
	private String id;
	private String name;
	private String maker;
	private String model;
	private String ipAddress;
	private DeviceType deviceType;
	private String serialNo;
	private DeviceOS os;
	private DeviceState state;
	private MaintainanceType maintainanceType;
	private Date maintainanceStartDateTime;
	private Date maintainanceEndDateTime;
	private DeviceStatus status;
	@DBRef
	private Server server;
	@DBRef
	private Client client;
	private Date clientSubscriptionStartsOn;
	private Date clientSubscriptionEndsOn;
}
