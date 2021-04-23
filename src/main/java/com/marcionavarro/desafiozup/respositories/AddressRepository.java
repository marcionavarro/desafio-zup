package com.marcionavarro.desafiozup.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcionavarro.desafiozup.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
