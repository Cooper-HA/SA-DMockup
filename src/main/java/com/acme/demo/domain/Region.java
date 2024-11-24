package com.acme.demo.domain;
//additonal annotations to stop data loops:
//https://stackoverflow.com/questions/46700641/jpa-entity-relationship-caused-endless-loop

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="REGIONS")
@NamedQueries({
	@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r"),
		@NamedQuery(name="Region.findAllWithDetail", query="SELECT DISTINCT r FROM Region r LEFT JOIN FETCH r.countries c WHERE c.region = r") 
})
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="REGIONS_REGIONID_GENERATOR", sequenceName="REGIONS_SEQ")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGIONS_REGIONID_GENERATOR")
	//@GeneratedValue(strategy=GenerationType.AUTO) 
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REGION_ID", unique=true, nullable=false, precision=22)
	private long regionId;

	@Column(name="REGION_NAME", length=25)
	private String regionName;

	//bi-directional many-to-one association to Country
//	@OneToMany(mappedBy="region", fetch = FetchType.EAGER)
	@OneToMany(mappedBy="region", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Country> countries;
	
	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setRegion(this);

		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setRegion(null);

		return country;
	}


//	public Region() {
//	}
//
//	public long getRegionId() {
//		return this.regionId;
//	}
//
//	public void setRegionId(long regionId) {
//		this.regionId = regionId;
//	}
//
//	public String getRegionName() {
//		return this.regionName;
//	}
//
//	public void setRegionName(String regionName) {
//		this.regionName = regionName;
//	}
//
//	public List<Country> getCountries() {
//		return this.countries;
//	}
//
//	public void setCountries(List<Country> countries) {
//		this.countries = countries;
//	}


//	@Override
//	public String toString() {
//		return "Region [regionId=" + regionId + ", regionName=" + regionName + "]";
//	}

	
}