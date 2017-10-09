package com.journaldev.spring.ivanium.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journaldev.ivanium.model.FactIngredientDTO;
import com.journaldev.ivanium.response.model.FactIngredient;
import com.journaldev.spring.ivanium.converter.DTOTOResponsePojoConverter;
import com.journaldev.spring.ivanium.dao.CustomerDao;
import com.journaldev.spring.ivanium.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	public CustomerDao customerDao;

	@Override
	public List<FactIngredient> getAllFactFragment() throws Exception {
		// TODO Auto-generated method stub
		List<FactIngredientDTO>factIngredientDTOs= customerDao.getAllFactFragment();
		List<FactIngredient> ingredient=DTOTOResponsePojoConverter.ConvertFactFragmentDTOtoFactFragment(factIngredientDTOs);
		return ingredient;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
