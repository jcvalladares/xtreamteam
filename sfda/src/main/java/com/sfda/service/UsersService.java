package com.sfda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sfda.entity.Users;
import com.sfda.repository.UsersLoginRepository;
import com.sfda.repository.UsersRepository;

@Component
public class UsersService {
	
	private UsersRepository usersRepository;
	
	private UsersLoginRepository usersLoginRepository;

    public UsersService(UsersRepository usersRepository, UsersLoginRepository usersLoginRepository) {
        this.usersRepository = usersRepository;
        this.usersLoginRepository = usersLoginRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    
    public Optional<antlr.collections.List> findUserById(String email) {
        return Optional.ofNullable(usersLoginRepository.findByEmail(email));
    }
    
    public Users saveUser(Users users) {
    	return usersRepository.save(users);
    }
    
    public Users resetPassword(Users users) {
    	List<Users> userList = usersRepository.findAll();
    	//TODO - scan all users and check if user exists
    	return userList.get(0);
    }
}