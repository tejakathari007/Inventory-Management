package com.quinnox.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "is_mobile_device", "type", "brand", "brand_code", "brand_url", "name" })
public class Device {

	@JsonProperty("is_mobile_device")
	private Boolean isMobileDevice;
	@JsonProperty("type")
	private String type;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("brand_code")
	private String brandCode;
	@JsonProperty("brand_url")
	private String brandUrl;
	@JsonProperty("name")
	private String name;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("is_mobile_device")
	public Boolean getIsMobileDevice() {
		return isMobileDevice;
	}

	@JsonProperty("is_mobile_device")
	public void setIsMobileDevice(Boolean isMobileDevice) {
		this.isMobileDevice = isMobileDevice;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("brand")
	public String getBrand() {
		return brand;
	}

	@JsonProperty("brand")
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@JsonProperty("brand_code")
	public String getBrandCode() {
		return brandCode;
	}

	@JsonProperty("brand_code")
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	@JsonProperty("brand_url")
	public String getBrandUrl() {
		return brandUrl;
	}

	@JsonProperty("brand_url")
	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
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
