package com.acme.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.demo.*;
import com.acme.demo.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
		List<Location> findByPostalCode(String postalCode); 
		List<Location> findByStateProvince(String stateProvince); 
}
