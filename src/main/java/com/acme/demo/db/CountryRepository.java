package com.acme.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.demo.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository {
	Country findByCountryName(String countryName); //List<Country> 

}
