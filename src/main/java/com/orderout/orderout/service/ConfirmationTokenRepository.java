package com.orderout.orderout.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.orderout.orderout.domain.ConfirmationToken;

public interface  ConfirmationTokenRepository  extends CrudRepository<ConfirmationToken, String>{
	
	Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
	
	void delete(ConfirmationToken confirmationToken);

}
