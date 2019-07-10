package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;

import com.orderout.orderout.domain.Group;
import com.orderout.orderout.domain.User;

public interface GroupDao extends CrudRepository<Group, Integer> {

}
