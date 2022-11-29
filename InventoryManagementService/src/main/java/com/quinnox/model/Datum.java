package com.quinnox.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "brand_id", "brand_name", "key", "device_list" })
public class Datum {

	@JsonProperty("brand_id")
	private Integer brandId;
	@JsonProperty("brand_name")
	private String brandName;
	@JsonProperty("key")
	private String key;
	@JsonProperty("device_list")
	private List<DeviceDetail> deviceList = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("brand_id")
	public Integer getBrandId() {
		return brandId;
	}

	@JsonProperty("brand_id")
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@JsonProperty("brand_name")
	public String getBrandName() {
		return brandName;
	}

	@JsonProperty("brand_name")
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("device_list")
	public List<DeviceDetail> getDeviceList() {
		return deviceList;
	}

	@JsonProperty("device_list")
	public void setDeviceList(List<DeviceDetail> deviceList) {
		this.deviceList = deviceList;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
