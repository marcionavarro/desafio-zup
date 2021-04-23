package com.marcionavarro.desafiozup.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcionavarro.desafiozup.config.ViaCEPClient;
import com.marcionavarro.desafiozup.entities.Address;

@RestController
@RequestMapping("/zipcode")
public class ZipCodeResource {

	private ViaCEPClient viaCepClient;

	
	public ResponseEntity<Address> getZipCode(@PathVariable String zipCode) {

		Address address = viaCepClient.SearchEndercore(zipCode);

		return address != null ? ResponseEntity.ok().body(address) : ResponseEntity.notFound().build();
	}

}
