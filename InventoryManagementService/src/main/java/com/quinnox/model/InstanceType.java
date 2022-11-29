package com.quinnox.model;

public enum InstanceType {

	T2_SMALL("t2.small"), T2_MEDIUM("t2.medium"), T2_LARGE("t2.large"), MAC_METAL("mac1.metal");

	String instanceType;

	InstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getInstanceType() {
		return instanceType;
	}

}
