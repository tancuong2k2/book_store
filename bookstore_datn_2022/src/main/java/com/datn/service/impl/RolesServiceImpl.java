package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.Roles;
import com.datn.repository.RolesRepo;
import com.datn.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesRepo repo;
	
	@Override
	public Roles findByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}
}
