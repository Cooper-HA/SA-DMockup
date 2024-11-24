package com.acme.demo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LocationEditForm {

	@NotNull
	@Size(min = 1, max = 140)
	private String LocationName;
	

	@NotNull
	private String locationPostalCode;

	@NotNull
	private Long countryId;

	@NotNull
	private Long LocationId;
	
//	public String getLocationName() {
//		return LocationName;
//	}
//
//	public void setLocationName(String LocationName) {
//		this.LocationName = LocationName;
//	}
//
//	public Long getCountryId() {
//		return countryId;
//	}
//
//	public void setCountryId(Long countryId) {
//		this.countryId = countryId;
//	}
//
//	public Long getLocationId() {
//		return LocationId;
//	}
//
//	public void setLocationId(Long LocationId) {
//		this.LocationId = LocationId;
//	}
	
}

