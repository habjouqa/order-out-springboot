package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;

import com.orderout.orderout.domain.Configuration;

public interface ConfigurationDao extends CrudRepository<Configuration, String> {

	
	
}
