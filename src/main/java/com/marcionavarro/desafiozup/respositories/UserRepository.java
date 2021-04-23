package com.marcionavarro.desafiozup.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcionavarro.desafiozup.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByCpf(String cpf);
	User findByEmail(String email);
}
