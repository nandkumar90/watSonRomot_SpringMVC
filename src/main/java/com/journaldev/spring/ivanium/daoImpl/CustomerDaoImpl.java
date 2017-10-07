package com.journaldev.spring.ivanium.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.journaldev.ivanium.model.FactIngredientDTO;
import com.journaldev.spring.ivanium.dao.CustomerDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<FactIngredientDTO> getAllFactFragment() throws Exception {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<FactIngredientDTO> criteriaQuery = builder.createQuery(FactIngredientDTO.class);
			Root<FactIngredientDTO> root = criteriaQuery.from(FactIngredientDTO.class);
			// Predicate prRootName = builder.equal(root.get("urno"), id);
			// criteriaQuery.where(prRootName);

			final TypedQuery<FactIngredientDTO> query = entityManager.createQuery(criteriaQuery);

			List<FactIngredientDTO> result = (List<FactIngredientDTO>) query.getSingleResult();
			return result;

		} catch (Exception ex) {
			logger.debug(ex.getMessage().toString());
			logger.debug(ex.getStackTrace().toString());
			throw new Exception(ex.getMessage().toString());

		}
	}

	@Override
	public void saveEmployee(FactIngredientDTO factIngredient) {
		entityManager.persist(factIngredient);

	}

}
