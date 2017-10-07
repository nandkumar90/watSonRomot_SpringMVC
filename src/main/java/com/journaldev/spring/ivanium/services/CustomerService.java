package com.journaldev.spring.ivanium.services;

import java.util.List;

import com.journaldev.ivanium.model.FactIngredientDTO;
import com.journaldev.ivanium.response.model.FactIngredient;

public interface CustomerService {
	public List<FactIngredient> getAllFactFragment() throws Exception;
}
