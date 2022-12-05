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
@JsonPropertyOrder({ "ua", "type", "brand", "name", "url", "os", "device", "browser", "crawler" })
public class DeviceInfo {

	@JsonProperty("ua")
	private String ua;
	@JsonProperty("type")
	private String type;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("name")
	private String name;
	@JsonProperty("url")
	private String url;
	@JsonProperty("os")
	private Os os;
	@JsonProperty("device")
	private Device device;
	@JsonProperty("browser")
	private Browser browser;
	@JsonProperty("crawler")
	private Crawler crawler;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("ua")
	public String getUa() {
		return ua;
	}

	@JsonProperty("ua")
	public void setUa(String ua) {
		this.ua = ua;
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

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("os")
	public Os getOs() {
		return os;
	}

	@JsonProperty("os")
	public void setOs(Os os) {
		this.os = os;
	}

	@JsonProperty("device")
	public Device getDevice() {
		return device;
	}

	@JsonProperty("device")
	public void setDevice(Device device) {
		this.device = device;
	}

	@JsonProperty("browser")
	public Browser getBrowser() {
		return browser;
	}

	@JsonProperty("browser")
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	@JsonProperty("crawler")
	public Crawler getCrawler() {
		return crawler;
	}

	@JsonProperty("crawler")
	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
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
