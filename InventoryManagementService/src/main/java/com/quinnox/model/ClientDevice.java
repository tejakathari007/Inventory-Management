package com.quinnox.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDevice {
	
	private String id;
	private String name;
	private String maker;
	private String model;
	private String IPAddress;
	private DeviceType deviceType;
	private String serialNo;
	private DeviceOS OS;
	private DeviceState state;
	private DeviceStatus status;
}
