package com.acme.demo.domain;



import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * The persistent class for the LOCATIONS database table.
 * 
 */
@Entity
@Table(name="LOCATIONS")
//@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="LOCATIONS_LOCATIONID_GENERATOR", sequenceName="LOCATIONS_SEQ")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_LOCATIONID_GENERATOR")
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LOCATION_ID", unique=true, nullable=false, precision=4)
	private Long locationId;

	@Column(nullable=false, length=30)
	private String city;

	@Column(name="POSTAL_CODE", length=12)
	private String postalCode;

	@Column(name="STATE_PROVINCE", length=25)
	private String stateProvince;

	@Column(name="STREET_ADDRESS", length=40)
	private String streetAddress;

	
	@OneToMany(mappedBy="location", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Employee> employees;
//	//bi-directional many-to-one association to Department
//	@OneToMany(mappedBy="location")
//	private List<Department> departments;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private Country country;

//	public Location() {
//	}
//
//	public Long getLocationId() {
//		return this.locationId;
//	}
//
//	public void setLocationId(Long locationId) {
//		this.locationId = locationId;
//	}
//
//	public String getCity() {
//		return this.city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getPostalCode() {
//		return this.postalCode;
//	}
//
//	public void setPostalCode(String postalCode) {
//		this.postalCode = postalCode;
//	}
//
//	public String getStateProvince() {
//		return this.stateProvince;
//	}
//
//	public void setStateProvince(String stateProvince) {
//		this.stateProvince = stateProvince;
//	}
//
//	public String getStreetAddress() {
//		return this.streetAddress;
//	}
//
//	public void setStreetAddress(String streetAddress) {
//		this.streetAddress = streetAddress;
//	}
//
//	public Country getCountry() {
//		return this.country;
//	}
//
//	public void setCountry(Country country) {
//		this.country = country;
//	}
//
//	@Override
//	public String toString() {
//		return "Location [locationId=" + locationId + ", city=" + city + ", postalCode=" + postalCode
//				+ ", stateProvince=" + stateProvince + ", streetAddress=" + streetAddress + "]";
//	}
//	
	

}