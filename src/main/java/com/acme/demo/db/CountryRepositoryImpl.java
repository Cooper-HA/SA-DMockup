package com.acme.demo.db;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.acme.demo.domain.Country;
import com.acme.demo.domain.Region;

public class CountryRepositoryImpl implements CountryCustomRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	CountryRepository countryRepository;
	
	public Country findOneWithDetails(long id) {
		Country country = null;

		country = countryRepository.findById(id).orElse(null);   //was findOne(id);  had to add optional to fix orElse(null) do to option return
		Query query = em.createQuery("SELECT r FROM Region r, Country c WHERE r = c.region AND c = ?1");
		query.setParameter(1, country);
		Region region = (Region) query.getSingleResult();
		country.setRegion(region);

		return country;
	}
	
	public Country populateOneWithDetails(Country country) {
		Query query = em.createQuery("SELECT r FROM Region r, Country c WHERE r = c.region AND c = ?1");
		query.setParameter(1, country);
		Region region = (Region) query.getSingleResult();
		country.setRegion(region);

		return country;
	}
	
	public String sayHello() {
		String result = "nothing";
		
		if(em == null)
			result = "was null";
		else
		{
			result = "Have em";
		}
		return result;
	}

}