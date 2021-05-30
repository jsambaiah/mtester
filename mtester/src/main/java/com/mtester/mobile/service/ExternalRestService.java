/**
 * 
 */
package com.mtester.mobile.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mtester.mobile.controller.MobileConstants;
import com.mtester.mobile.dto.MobileDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author samba
 *
 */
@Service
public class ExternalRestService {
	Logger logger = LoggerFactory.getLogger(ExternalRestService.class);
	private final RestTemplate restTemplate;

	public ExternalRestService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	/**
	 * Fetches given mobile device info from external system. If it fails to fetch
	 * data gets same info from other reliable system.
	 * 
	 * @param name can't be {@literal null}
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "reliable")
	public MobileDTO populateDeviceInfoFromExternal(String name) {
		// Mobile name saved on internal DB is combination of brand and device name.
		// Splitting same to call FONOAPI
		String brand = name.substring(0, name.indexOf(" ") + 1);
		String device = name.substring(name.indexOf(" "));
		StringBuffer URL = new StringBuffer(MobileConstants.FONOAPI_DEVICEAPIURL);
		URL.append("&brand=" + brand).append("&device=" + device);
		ResponseEntity<MobileDTO[]> response = restTemplate.getForEntity(URL.toString(), MobileDTO[].class);
		MobileDTO[] employees = response.getBody();

		return Arrays.asList(employees).stream().findAny().get();
	}

	public MobileDTO reliable(String name) {
		// TODO fetch device info from reliable source. Not implemented anything as
		// technology and bands info already fetched from table
		logger.warn("Failed to fetching mobile meta data from FONOAPI, trying to fetch same from internal system");
		return null;
	}
}
