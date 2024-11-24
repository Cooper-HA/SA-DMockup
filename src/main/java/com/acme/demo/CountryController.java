package com.acme.demo;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.Errors;

import com.acme.demo.db.CountryRepository;
import com.acme.demo.db.RegionRepository;
import com.acme.demo.domain.Country;
import com.acme.demo.domain.Location;
import com.acme.demo.domain.Region;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/country")
public class CountryController {

	private CountryRepository countryRepository;
	private RegionRepository regionRepository;
	
	@Autowired
	public CountryController(CountryRepository countryRepository, RegionRepository regionRepository) {
	  this.countryRepository = countryRepository;
	  this.regionRepository = regionRepository;
	}
	
	//@RequestMapping(method=GET, headers={"Accept=application/xml", "application/ajax"})
	//@RequestMapping(method=GET, headers={"Accept=application/xml"})
	//@RequestMapping(method=GET)
	//@RequestMapping(method = GET,  produces = { "application/xml", "application/ajax"})
	@RequestMapping(value="/api", method=GET)
	public @ResponseBody List<Country>  getCountryList(Model model) {
      List<Country> countryList = countryRepository.findAll();
	  //model.addAttribute(countryList);
	  return countryList;
	}
	
	@RequestMapping(value="/api/{id}", method=GET)
	public @ResponseBody Country getCountryById(@PathVariable Long id) {
      Country country = countryRepository.findById(id).orElse(null);
	  System.err.println("SIZE: " + country.getLocations().size());
	  for(Location l: country.getLocations())
		  System.err.println("Location City: " + l.getCity());
	  return country;
	}
	
	@RequestMapping(value="/api/create", method=POST)
	public @ResponseBody Country submitCountryApi( @RequestBody @Valid CountryForm countryForm, Errors errors) {
		System.err.println(countryForm.getCountryName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Country country = new Country();
		country.setCountryName(countryForm.getCountryName());
		
		Region region = regionRepository.findById(countryForm.getRegionId()).orElse(null);
				
//		if (errors.hasErrors() | region == null) {
//		    return "country/create";
//		}
		country.setRegion(region);
		country = countryRepository.save(country);
	 
		return country;
	}
	
	@RequestMapping(value="/list", method=GET)
	public String getCountries(Model model) {
      List<Country> countries = countryRepository.findAll();
	  model.addAttribute("countries", countries);
	  System.err.println("SIZE: " + countries.size());
	  for(Country c: countries)
		  System.err.println("Name: " + c.getCountryName());
	  return "country/countrylist";
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public String getCountryById(@PathVariable Long id, Model model) {
      Country country = countryRepository.findById(id).orElse(null);
	  model.addAttribute("cou", country);
	  model.addAttribute("locationCount", country.getLocations().size());
	  System.err.println("SIZE: " + country.getLocations().size());
	  for(Location l: country.getLocations())
		  System.err.println("Location City: " + l.getCity());
	  return "country/details";
	}
	
	@RequestMapping(value="/add", method=GET)
	public String showCreateForm(Model model) {
      List<Region> regions = regionRepository.findAll();
      model.addAttribute("regions", regions);
	  model.addAttribute("countryForm", new CountryForm());	
	  return "country/add";
	}
	

	
	@RequestMapping(value="/create", method=POST)
	public String submitCountry( @Valid CountryForm countryForm, Errors errors) {
		System.err.println(countryForm.getCountryName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Country country = new Country();
		country.setCountryName(countryForm.getCountryName());
		
		Region region = regionRepository.findById(countryForm.getRegionId()).orElse(null);
				
		if (errors.hasErrors() | region == null) {
		    return "country/create";
		}
		country.setRegion(region);
		country = countryRepository.save(country);
	 
	  //spitterRepository.save(spitter);
	  return "redirect:/country/" + country.getCountryId();
	  
	}


	@RequestMapping(value="/edit/{id}", method=GET)
	public String showEditForm(@PathVariable Long id, Model model) {
	  Country country = countryRepository.findById(id).orElse(null);
      List<Region> regions = regionRepository.findAll();
      model.addAttribute("regions", regions);
      CountryEditForm form =  new CountryEditForm();
      form.setCountryId(country.getCountryId());
      form.setCountryName(country.getCountryName());
      form.setRegionId(country.getRegion().getRegionId());
      
	  model.addAttribute("countryEditForm", form);	
	  return "country/edit";
	}
	

	
	@RequestMapping(value="/submitedit", method=POST)
	public String submitEditCountry( @Valid CountryEditForm countryEditForm, Errors errors) {
		System.err.println(countryEditForm.getCountryName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Country country = countryRepository.findById(countryEditForm.getCountryId()).orElse(null);
		country.setCountryName(countryEditForm.getCountryName());
		
		Region region = regionRepository.findById(countryEditForm.getRegionId()).orElse(null);
				
		if (errors.hasErrors() | region == null) {
		    return "country/create";
		}
		country.setRegion(region);
		country = countryRepository.save(country);
	 
	  //spitterRepository.save(spitter);
	  return "redirect:/country/" + country.getCountryId();
	  
	}
	
	@RequestMapping(value="/api", method=POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public @ResponseBody Country postCountry( @RequestBody Country country, HttpServletResponse response) {
		System.err.println(country.getCountryName());
		//System.err.println("Has errors: " + errors.hasErrors()); 
		
		Region region = regionRepository.findById(country.getRegion().getRegionId()).orElse(null);
		country.setRegion(region);
//		if (errors.hasErrors() | region == null) {
//		    return "create";
//		}
		country = countryRepository.save(country);

	  return country;
	  
	}

}
