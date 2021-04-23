package com.marcionavarro.desafiozup.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcionavarro.desafiozup.config.ViaCEPClient;
import com.marcionavarro.desafiozup.entities.Address;
import com.marcionavarro.desafiozup.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressResource {

	@Autowired
	private AddressService service;
	
	@Autowired
	private ViaCEPClient viaCepClient;

	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		List<Address> Addresss = service.findAll();
		return ResponseEntity.ok().body(Addresss);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> findById(@PathVariable Long id) {
		Optional<Address> address = Optional.ofNullable(service.findById(id));

		if (address.isPresent()) {
			return ResponseEntity.ok(address.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Address> insert(@Valid @RequestBody Address address) {
		address = service.insert(address);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId())
				.toUri();
		return ResponseEntity.created(uri).body(address);
	}
	
	
	@GetMapping("/zipcode/{zipCode}/json")
	public ResponseEntity<Address> getZipCode(@PathVariable String zipCode) {
		
		Address address = viaCepClient.SearchEndercore(zipCode);
		return address != null ? ResponseEntity.ok().body(address) : ResponseEntity.notFound().build();
	}
	

}
