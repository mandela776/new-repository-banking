package com.mandela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mandela.model.User;
import com.mandela.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository repo;


	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByAccountNo(Long account) {
		return repo.findByAccountno(account);
	}
	
	public User fetchUserByAccountNoAndPinCode(Long account , Long pin) {
		return repo.findByAccountnoAndPincode(account, pin);
	}
	
	
	
}
