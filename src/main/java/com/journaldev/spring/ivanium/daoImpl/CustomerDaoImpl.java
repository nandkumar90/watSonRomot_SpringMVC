package com.journaldev.spring.ivanium.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.journaldev.spring.ivanium.dao.CustomerDao;
import com.journaldev.spring.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext(unitName = "cedaPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
