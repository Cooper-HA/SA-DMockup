package com.acme.demo.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.acme.demo.domain.Country;
import com.acme.demo.domain.Region;

public class RegionRepositoryImpl implements RegionCustomRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	RegionRepository regionRepository;
	
	public Region findOneWithDetails(long id) {
		Region region = null;

		region = regionRepository.findById(id).orElse(null);
		Query query = em.createQuery("SELECT c FROM Country c WHERE c.region = ?1");
		query.setParameter(1, region);
		List<Country> countries = (List<Country>) query.getResultList();
		region.setCountries(countries);

		return region;
	}

	public Region populateOneWithDetails(Region region) {
		Query query = em.createQuery("SELECT c FROM Country c WHERE c.region = ?1");
		query.setParameter(1, region);
		List<Country> countries = (List<Country>) query.getResultList();
		region.setCountries(countries);

		return region;
	}
	
	public long getCountryCount(long regionId) {
		long result = -1;
		Query query = em.createQuery("SELECT COUNT(c.countryId) FROM Country c WHERE c.region.regionId = ?1 ");
		query.setParameter(1, regionId);
		result = ((Long)query.getSingleResult()).longValue();   //.getSingleResult();
		return result;
		}
	
	@Transactional
	public void transferCountryToOtherRegion(Country country, Region currentRegion, Region newRegion) { // throws Exception {

		currentRegion = populateOneWithDetails(currentRegion);
		newRegion = populateOneWithDetails(newRegion);
		currentRegion.getCountries().remove(country);
		newRegion.getCountries().add(country);
		country.setRegion(newRegion);
		em.merge(currentRegion);
		em.merge(country);
		em.merge(newRegion);
		
	}


}