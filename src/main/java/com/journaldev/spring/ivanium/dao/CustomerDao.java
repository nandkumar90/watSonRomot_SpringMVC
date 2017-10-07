package com.journaldev.spring.ivanium.dao;

import java.util.List;

import com.journaldev.ivanium.model.FactIngredientDTO;

public interface CustomerDao {
	public List<FactIngredientDTO> getAllFactFragment() throws Exception;
	
	public void saveEmployee(FactIngredientDTO factIngredient);

}
