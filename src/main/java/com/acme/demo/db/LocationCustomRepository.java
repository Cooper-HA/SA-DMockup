package com.acme.demo.db;

import com.acme.demo.domain.Location;

public interface LocationCustomRepository {
	public String sayHello();
	public Location findOneWithDetails(long id);
	public Location populateOneWithDetails(Location country);
}
