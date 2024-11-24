package com.acme.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acme.demo.db.RegionCustomRepository;
import com.acme.demo.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionCustomRepository {
	List<Region> findAllWithDetail();
	
	@Query("SELECT COUNT(c.countryId) FROM Country c WHERE c.region = ?1") 
	Long getCountryCount2(Region region);
}
