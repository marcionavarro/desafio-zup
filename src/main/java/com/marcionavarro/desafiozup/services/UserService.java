package com.marcionavarro.desafiozup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcionavarro.desafiozup.entities.User;
import com.marcionavarro.desafiozup.respositories.UserRepository;
import com.marcionavarro.desafiozup.services.exceptions.BusinessException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		
		return user.orElseThrow(() -> new BusinessException("Não existe um usuário com este ID"));
	}

	public User insert(User user) {

		User cpfExistis = repository.findByCpf(user.getCpf());
		if (cpfExistis != null && !cpfExistis.equals(user)) {
			throw new BusinessException("Já existe um cliente cadastrado com este cpf");
		}

		User userExistis = repository.findByEmail(user.getEmail());
		if (userExistis != null && !userExistis.equals(user)) {
			throw new BusinessException("Já existe um cliente cadastrado com este e-email");
		}
		
		return repository.save(user);
		
	}
}
