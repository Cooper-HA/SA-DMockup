package com.acme.demo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LocationForm {

	@NotNull
	@Size(min=1, max=140)
	private String locationName;
	
	@NotNull
	private String locationPostalCode;
	
	private Long locationId;
	  
	@NotNull
	private Long countryId;

//	public String getLocationName() {
//		return locationName;
//	}
//
//	public void setLocationName(String locationName) {
//		this.locationName = locationName;
//	}
//
//	public Long getLocationId() {
//		return locationId;
//	}
//
//	public void setLocationId(Long locationId) {
//		this.locationId = locationId;
//	}
//	
//	public Long getCountryId() {
//		return countryId;
//	}
//
//	public void setCountryId(Long countryId) {
//		this.countryId = countryId;
//	}
}