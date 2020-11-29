package com.sfda.util;

import java.util.Date;

import com.sfda.entity.UserType;
import com.sfda.entity.Users;

public class UserDetailsValidator {

	public boolean isValidDonor(Users users) {
		return "Y".equalsIgnoreCase(users.getIsValidated()) && UserType.DONOR.name().equalsIgnoreCase(users.getType());
	}

	public boolean isValidAdultUser(Users users) {
		Date today = new Date();
		int diffInDays = (int) ((today.getTime() - users.getBirthDate().getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays > 6570;
	}
	
	public boolean isQRGenertedForUser(Users users) {
		return isValidAdultUser(users) && "Y".equalsIgnoreCase(users.getIsQRCodeGenerated());
	}
}