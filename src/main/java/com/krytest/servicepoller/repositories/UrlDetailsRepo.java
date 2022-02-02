package com.krytest.servicepoller.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.krytest.servicepoller.entities.UrlDetails;

public interface UrlDetailsRepo extends CrudRepository<UrlDetails, Long>{

	UrlDetails[] findByUserId(String userId);
	
	Optional<UrlDetails> findById(Long id);
	
}
