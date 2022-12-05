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
@JsonPropertyOrder({ "is_crawler", "category", "last_seen" })
public class Crawler {

	@JsonProperty("is_crawler")
	private Boolean isCrawler;
	@JsonProperty("category")
	private Object category;
	@JsonProperty("last_seen")
	private Object lastSeen;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("is_crawler")
	public Boolean getIsCrawler() {
		return isCrawler;
	}

	@JsonProperty("is_crawler")
	public void setIsCrawler(Boolean isCrawler) {
		this.isCrawler = isCrawler;
	}

	@JsonProperty("category")
	public Object getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(Object category) {
		this.category = category;
	}

	@JsonProperty("last_seen")
	public Object getLastSeen() {
		return lastSeen;
	}

	@JsonProperty("last_seen")
	public void setLastSeen(Object lastSeen) {
		this.lastSeen = lastSeen;
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