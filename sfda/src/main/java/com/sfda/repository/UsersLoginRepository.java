package com.sfda.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sfda.entity.Users;

@RepositoryRestResource()
public interface UsersLoginRepository extends Repository<Users, String> {
	
	  Users findByEmail(String email);
}