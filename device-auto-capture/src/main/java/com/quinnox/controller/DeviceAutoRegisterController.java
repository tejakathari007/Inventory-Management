package com.quinnox.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.quinnox.model.DeviceInfo;

@CrossOrigin
@RestController
public class DeviceAutoRegisterController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${userStack.api}")
	private String userStackURL;

	@Value("${userStack.apikey}")
	private String apiKey;

	private Map<String, Map<String, String>> autoCaptured = new HashMap<String, Map<String, String>>();

	private Map<String, Map<String, String>> userStack = new HashMap<String, Map<String, String>>();

	@GetMapping(value = "/autoDeviceAdd/{uniqueId}", produces = MediaType.TEXT_HTML_VALUE)
	public String genDeviceCaptureAPI(@PathVariable String uniqueId, @RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			System.out.println(key + " : " + value);
		});
		autoCaptured.put(uniqueId, headers);
		System.out.println("headers Info*************");
		System.out.println(headers);
		System.out.println("headers Info End*************");
		Map<String, String> info = getDeviceInfo(headers.get("user-agent"), uniqueId);
		System.out.println("userStack Info*************");
		System.out.println(info);
		System.out.println("userStack Info End*************");
		userStack.put(uniqueId, info);

		return "<html><h2>Device will be captured shortly, uniqueId: " + uniqueId + "</h2></html>";
	}

	@GetMapping("/get/autoCapturedDevice/{uniqueId}")
	public Map<String, String> genDeviceCaptureAPI(@PathVariable String uniqueId) {
		Map<String, String> map = autoCaptured.get(uniqueId);
		if (map == null) {
			return new HashMap<>();
		}
		return userStack.get(uniqueId);

	}

	@GetMapping("/clearAll/headers")
	public String clearAllAutoCapturedDevices() {
		autoCaptured.clear();
		return "All the devices were cleared";

	}

	@GetMapping("/clearAll/userStack")
	public String clearAutoCapturedDevices() {
		userStack.clear();
		return "All the userInfo were cleared";

	}

	@GetMapping("/getAll/headers")
	public Map<String, Map<String, String>> getAllAutoCapturedDevices() {
		return autoCaptured;

	}

	@GetMapping("/getAll/userStack")
	public Map<String, Map<String, String>> getAllCapturedDevices() {
		return userStack;

	}

	private Map<String, String> getDeviceInfo(String userAgent, String uniqueId) {
		Map<String, String> captured = new HashMap<>();
		captured.put("uniqueId", uniqueId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<DeviceInfo> deviceList = restTemplate.exchange(userStackURL + "?access_key={key}&ua={ua}",
				HttpMethod.GET, entity, DeviceInfo.class, apiKey, userAgent);

		System.out.println(deviceList.getBody());
		if (deviceList.getBody().getDevice().getIsMobileDevice()) {
			if (deviceList.getBody().getOs().getFamily() != null)
				captured.put("os", deviceList.getBody().getOs().getFamily().toUpperCase());
			captured.put("maker", deviceList.getBody().getBrand());
			captured.put("model", deviceList.getBody().getName());
		}

		return captured;

	}

	@GetMapping("/test")
	public Map<String, String> getAllAutoCapturedDevices(@RequestBody String ua) {
		return getDeviceInfo(ua, UUID.randomUUID().toString());

	}

}
