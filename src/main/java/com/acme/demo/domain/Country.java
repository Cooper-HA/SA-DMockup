package com.acme.demo.domain;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="COUNTRIES")
//@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Country  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="COUNTRIES_COUNTRYID_GENERATOR", sequenceName="COUNTRIES_SEQ")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRIES_COUNTRYID_GENERATOR")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COUNTRY_ID", unique=true, nullable=false, length=2)
	private Long countryId;

	@Column(name="COUNTRY_NAME", length=40)
	private String countryName;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="REGION_ID")
	private Region region;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Location> locations;
	
	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setCountry(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setCountry(null);

		return location;
	}
//	public Country() {
//	}

//	public Long getCountryId() {
//		return this.countryId;
//	}
//
//	public void setCountryId(Long countryId) {
//		this.countryId = countryId;
//	}
//
//	public String getCountryName() {
//		return this.countryName;
//	}
//
//	public void setCountryName(String countryName) {
//		this.countryName = countryName;
//	}
//
//	public Region getRegion() {
//		return this.region;
//	}
//
//	public void setRegion(Region region) {
//		this.region = region;
//	}
//
//	@Override
//	public String toString() {
//		if(region == null)
//			return "Country [countryId=" + countryId + ", countryName=" + countryName + "]";
//		else
//			return "Country [countryId=" + countryId + ", countryName=" + countryName + ", regionName=" + region.getRegionName() + "]";
//	}
//
//	public List<Location> getLocations() {
//		return this.locations;
//	}
//
//	public void setLocations(List<Location> locations) {
//		this.locations = locations;
//	}



}