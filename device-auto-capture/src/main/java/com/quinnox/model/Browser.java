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
@JsonPropertyOrder({ "name", "version", "version_major", "engine" })
public class Browser {

	@JsonProperty("name")
	private String name;
	@JsonProperty("version")
	private String version;
	@JsonProperty("version_major")
	private String versionMajor;
	@JsonProperty("engine")
	private String engine;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("version_major")
	public String getVersionMajor() {
		return versionMajor;
	}

	@JsonProperty("version_major")
	public void setVersionMajor(String versionMajor) {
		this.versionMajor = versionMajor;
	}

	@JsonProperty("engine")
	public String getEngine() {
		return engine;
	}

	@JsonProperty("engine")
	public void setEngine(String engine) {
		this.engine = engine;
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
