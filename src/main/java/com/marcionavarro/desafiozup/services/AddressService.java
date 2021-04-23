package com.marcionavarro.desafiozup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcionavarro.desafiozup.entities.Address;
import com.marcionavarro.desafiozup.respositories.AddressRepository;
import com.marcionavarro.desafiozup.services.exceptions.BusinessException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address findById(Long id) {
		Optional<Address> address = repository.findById(id);
		return address.orElseThrow(() -> new BusinessException("Não existe um endereço com este Id"));
	}

	public Address insert(Address address) {
		return repository.save(address);
	}

}
