package com.acme.demo.db;

import com.acme.demo.domain.Country;
import com.acme.demo.domain.Region;

public interface RegionCustomRepository {
	public Region findOneWithDetails(long id);
	public Region populateOneWithDetails(Region region);
	public void transferCountryToOtherRegion(Country country, Region currentRegion, Region newRegion);
	public long getCountryCount(long regionId);
}
