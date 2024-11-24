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

import com.acme.demo.db.LocationRepository;
import com.acme.demo.db.CountryRepository;
import com.acme.demo.db.CountryRepository;
import com.acme.demo.domain.Location;
import com.acme.demo.domain.Country;
import com.acme.demo.domain.Country;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/location")
public class LocationController {

	private LocationRepository locationRepository;
	private CountryRepository countryRepository;
	
	@Autowired
	public LocationController(LocationRepository locationRepository, CountryRepository countryRepository) {
	  this.locationRepository = locationRepository;
	  this.countryRepository = countryRepository;
	}
	
	//@RequestMapping(method=GET, headers={"Accept=application/xml", "application/ajax"})
	//@RequestMapping(method=GET, headers={"Accept=application/xml"})
	//@RequestMapping(method=GET)
	//@RequestMapping(method = GET,  produces = { "application/xml", "application/ajax"})
	@RequestMapping(value="/api", method=GET)
	public @ResponseBody List<Location>  getLocationList(Model model) {
      List<Location> locationList = locationRepository.findAll();
	  //model.addAttribute(locationList);
	  return locationList;
	}
	
	@RequestMapping(value="/api/{id}", method=GET)
	public @ResponseBody Location getLocationById(@PathVariable Long id) {
      Location location = locationRepository.findById(id).orElse(null);
	  return location;
	}
	
	@RequestMapping(value="/api/create", method=POST)
	public @ResponseBody Location submitLocationApi( @RequestBody @Valid LocationForm locationForm, Errors errors) {
		System.err.println("Has errors: " + errors.hasErrors()); 
		Location location = new Location();
		location.setCity(locationForm.getLocationName());
		
		Country country = countryRepository.findById(locationForm.getLocationId()).orElse(null);
				
//		if (errors.hasErrors() | country == null) {
//		    return "location/create";
//		}
		location.setCountry(country);
		location = locationRepository.save(location);
	 
		return location;
	}
	
	@RequestMapping(value="/list", method=GET)
	public String getCountries(Model model) {
      List<Location> locations = locationRepository.findAll();
	  model.addAttribute("locations", locations);
	  System.err.println("SIZE: " + locations.size());
	  for(Location l: locations)
		  System.err.println("Name: " + l.getCity());
	  return "location/locationlist";
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public String getLocationById(@PathVariable Long id, Model model) {
      Location location = locationRepository.findById(id).orElse(null);
	  model.addAttribute("cou", location);
	  model.addAttribute("EmplayeeCount", location.getEmployees().size());

	  return "location/details";
	}
	
	@RequestMapping(value="/add", method=GET)
	public String showCreateForm(Model model) {
      List<Country> countrys = countryRepository.findAll();
      model.addAttribute("countrys", countrys);
	  model.addAttribute("locationForm", new LocationForm());	
	  return "location/add";
	}
	

	
	@RequestMapping(value="/create", method=POST)
	public String submitLocation( @Valid LocationForm locationForm, Errors errors) {
		System.err.println(locationForm.getLocationName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Location location = new Location();
		location.setCity(locationForm.getLocationName());
		
		Country country = countryRepository.findById(locationForm.getCountryId()).orElse(null);
				
		if (errors.hasErrors() | country == null) {
		    return "location/create";
		}
		location.setCountry(country);
		location = locationRepository.save(location);
	 
	  //spitterRepository.save(spitter);
	  return "redirect:/location/" + location.getLocationId();
	  
	}


	@RequestMapping(value="/edit/{id}", method=GET)
	public String showEditForm(@PathVariable Long id, Model model) {
	  Location location = locationRepository.findById(id).orElse(null);
      List<Country> countrys = countryRepository.findAll();
      model.addAttribute("countrys", countrys);
      LocationEditForm form =  new LocationEditForm();
      form.setLocationId(location.getLocationId());
      form.setLocationName(location.getCity());
      form.setCountryId(location.getCountry().getCountryId());
      
	  model.addAttribute("locationEditForm", form);	
	  return "location/edit";
	}
	

	
	@RequestMapping(value="/submitedit", method=POST)
	public String submitEditLocation( @Valid LocationEditForm locationEditForm, Errors errors) {
		System.err.println(locationEditForm.getLocationName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Location location = locationRepository.findById(locationEditForm.getLocationId()).orElse(null);
		location.setCity(locationEditForm.getLocationName());
		location.setPostalCode(locationEditForm.getLocationPostalCode());
		Country country = countryRepository.findById(locationEditForm.getCountryId()).orElse(null);
				
		if (errors.hasErrors() | country == null) {
		    return "location/create";
		}
		location.setCountry(country);
		location = locationRepository.save(location);
	 
	  //spitterRepository.save(spitter);
	  return "redirect:/location/" + location.getLocationId();
	  
	}
	
	@RequestMapping(value="/api", method=POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public @ResponseBody Location postLocation( @RequestBody Location location, HttpServletResponse response) {
		System.err.println(location.getCity());
		//System.err.println("Has errors: " + errors.hasErrors()); 
		
		Country country = countryRepository.findById(location.getCountry().getCountryId()).orElse(null);
		location.setCountry(country);
//		if (errors.hasErrors() | country == null) {
//		    return "create";
//		}
		location = locationRepository.save(location);

	  return location;
	  
	}

}
