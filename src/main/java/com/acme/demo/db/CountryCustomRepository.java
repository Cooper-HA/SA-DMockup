package com.acme.demo.db;

import com.acme.demo.domain.Country;

public interface CountryCustomRepository {
	public String sayHello();
	public Country findOneWithDetails(long id);
	public Country populateOneWithDetails(Country country);
}
