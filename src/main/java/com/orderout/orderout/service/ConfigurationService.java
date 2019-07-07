package com.orderout.orderout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.ConfigurationDao;

@Service
public class ConfigurationService {

	@Autowired
	private ConfigurationDao userDao;

	public ConfigurationDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ConfigurationDao userDao) {
		this.userDao = userDao;
	}

}
