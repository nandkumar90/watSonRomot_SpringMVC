package com.journaldev.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class IvaniumController {
private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	Map<String, String> contextMap = new HashMap<String, String>();
	
	
	@RequestMapping(value = EmpRestURIConstants.CUSTOMER_DETAILS, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getCustomerDetail() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic YjhkYTdhYjUtNTdhYy00MjQxLTk0YTktNmE1MDM0YmYyOWEzOndnTUZoeTZJVnl3aQ==");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate=new RestTemplate();
	    ResponseEntity<String> result = restTemplate.exchange("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/5cc74062-e7ec-4057-a94d-4ae112ca6462?version=2017-05-06&include_count=false", HttpMethod.GET, request, String.class);
		
		return result;
	}	
	
}
