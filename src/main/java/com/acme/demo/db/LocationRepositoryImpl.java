package com.acme.demo.db;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.acme.demo.domain.Location;
import com.acme.demo.domain.Country;

public class LocationRepositoryImpl implements LocationCustomRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	LocationRepository locationRepository;
	
	public Location findOneWithDetails(long id) {
		Location location = null;

		location = locationRepository.findById(id).orElse(null);   //was findOne(id);  had to add optional to fix orElse(null) do to option return
		Query query = em.createQuery("SELECT r FROM Country r, Location c WHERE r = c.country AND c = ?1");
		query.setParameter(1, location);
		Country country = (Country) query.getSingleResult();
		location.setCountry(country);

		return location;
	}
	
	public Location populateOneWithDetails(Location location) {
		Query query = em.createQuery("SELECT r FROM Country r, Location c WHERE r = c.country AND c = ?1");
		query.setParameter(1, location);
		Country country = (Country) query.getSingleResult();
		location.setCountry(country);

		return location;
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