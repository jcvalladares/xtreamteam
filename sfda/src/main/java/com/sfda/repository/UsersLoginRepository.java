package com.sfda.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sfda.entity.Users;

import antlr.collections.List;

@RepositoryRestResource()
public interface UsersLoginRepository extends Repository<Users, String> {
	
	  List findByEmail(String email);
}