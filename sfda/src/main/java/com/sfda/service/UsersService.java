package com.sfda.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

	public Users findUserById(String email, String password) {
		Optional<Users> loggedinUsers = Optional.ofNullable(usersLoginRepository.findByEmail(email));
		if (loggedinUsers != null && StringUtils.equals(loggedinUsers.get().getPassword(), password)) {
			return loggedinUsers.get();
		}

		return null;
	}
	public Users findUser(String email) {
		Optional<Users> loggedinUsers = Optional.ofNullable(usersLoginRepository.findByEmail(email));
		if(loggedinUsers.isPresent()){
			return loggedinUsers.get();
		}
		return null;
	}

	public Users saveUser(Users users) {
		return usersRepository.save(users);
	}

	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	public Users resetPassword(Users users) {
		List<Users> userList = usersRepository.findAll();
		// TODO - scan all users and check if user exists
		return userList.get(0);
	}
}