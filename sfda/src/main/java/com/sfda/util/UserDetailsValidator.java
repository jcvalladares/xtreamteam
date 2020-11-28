package com.sfda.util;

import com.sfda.entity.UserType;
import com.sfda.entity.Users;

public class UserDetailsValidator {

	public boolean isValidDonor(Users users) {
		return "Y".equalsIgnoreCase(users.getIsValidated()) && UserType.DONOR.name().equalsIgnoreCase(users.getType());
	}

}