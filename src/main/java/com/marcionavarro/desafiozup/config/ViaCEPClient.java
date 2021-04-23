package com.marcionavarro.desafiozup.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marcionavarro.desafiozup.entities.Address;

@FeignClient(url = "https://viacep.com.br/ws/", name="zipcode")
public interface ViaCEPClient {
	
	@GetMapping("{zipCode}/json")
	Address SearchEndercore(@PathVariable("zipCode") String zipCode);
}
