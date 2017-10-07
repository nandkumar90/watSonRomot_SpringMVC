package com.journaldev.spring.controller;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.ivanium.response.model.FactIngredient;
import com.journaldev.spring.ivanium.services.CustomerService;

@Controller
public class IvaniumController {
private static final Logger logger = LoggerFactory.getLogger(IvaniumController.class);
	
	 @Autowired
	  private CustomerService customerService;

	@RequestMapping(value = EmpRestURIConstants.CUSTOMER_DETAILS, method = RequestMethod.GET)
	public @ResponseBody Response<FactIngredient> getCustomerDetail() throws Exception {
		List<FactIngredient>FactIngredients= customerService.getAllFactFragment();
		Response<FactIngredient> response=new Response<FactIngredient>();
		response.setContent(FactIngredients);
		return response;
	}	
	
}
