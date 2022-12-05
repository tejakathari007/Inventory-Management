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
@JsonPropertyOrder({ "name", "code", "url", "family", "family_code", "family_vendor", "icon", "icon_large" })
public class Os {

	@JsonProperty("name")
	private String name;
	@JsonProperty("code")
	private String code;
	@JsonProperty("url")
	private String url;
	@JsonProperty("family")
	private String family;
	@JsonProperty("family_code")
	private String familyCode;
	@JsonProperty("family_vendor")
	private String familyVendor;
	@JsonProperty("icon")
	private String icon;
	@JsonProperty("icon_large")
	private String iconLarge;
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

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("family")
	public String getFamily() {
		return family;
	}

	@JsonProperty("family")
	public void setFamily(String family) {
		this.family = family;
	}

	@JsonProperty("family_code")
	public String getFamilyCode() {
		return familyCode;
	}

	@JsonProperty("family_code")
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	@JsonProperty("family_vendor")
	public String getFamilyVendor() {
		return familyVendor;
	}

	@JsonProperty("family_vendor")
	public void setFamilyVendor(String familyVendor) {
		this.familyVendor = familyVendor;
	}

	@JsonProperty("icon")
	public String getIcon() {
		return icon;
	}

	@JsonProperty("icon")
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@JsonProperty("icon_large")
	public String getIconLarge() {
		return iconLarge;
	}

	@JsonProperty("icon_large")
	public void setIconLarge(String iconLarge) {
		this.iconLarge = iconLarge;
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