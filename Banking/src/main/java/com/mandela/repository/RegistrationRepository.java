package com.mandela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandela.model.User;

public interface RegistrationRepository extends JpaRepository<User, Integer> {

	public User findByAccountno(Long accountno);

	public User findByAccountnoAndPincode(long accountno, long pincode);

	
}
