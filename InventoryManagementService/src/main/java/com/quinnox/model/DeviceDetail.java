package com.quinnox.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"device_id",
"device_name",
"device_type",
"device_image",
"key"
})
@Generated("jsonschema2pojo")
public class DeviceDetail {

@JsonProperty("device_id")
private Integer deviceId;
@JsonProperty("device_name")
private String deviceName;
@JsonProperty("device_type")
private String deviceType;
@JsonProperty("device_image")
private String deviceImage;
@JsonProperty("key")
private String key;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("device_id")
public Integer getDeviceId() {
return deviceId;
}

@JsonProperty("device_id")
public void setDeviceId(Integer deviceId) {
this.deviceId = deviceId;
}

@JsonProperty("device_name")
public String getDeviceName() {
return deviceName;
}

@JsonProperty("device_name")
public void setDeviceName(String deviceName) {
this.deviceName = deviceName;
}

@JsonProperty("device_type")
public String getDeviceType() {
return deviceType;
}

@JsonProperty("device_type")
public void setDeviceType(String deviceType) {
this.deviceType = deviceType;
}

@JsonProperty("device_image")
public String getDeviceImage() {
return deviceImage;
}

@JsonProperty("device_image")
public void setDeviceImage(String deviceImage) {
this.deviceImage = deviceImage;
}

@JsonProperty("key")
public String getKey() {
return key;
}

@JsonProperty("key")
public void setKey(String key) {
this.key = key;
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
