package com.sfda.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sfda.entity.UserType;
import com.sfda.entity.Users;

public class UserDetailsValidator {

	public static boolean isValidDonor(Users users) {
		return "Y".equalsIgnoreCase(users.getIsValidated()) && UserType.DONOR.name().equalsIgnoreCase(users.getType());
	}

	public static boolean isValidAdultUser(Users users) {
		Date today = new Date();
		int diffInDays = (int) ((today.getTime() - users.getBirthDate().getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays > 6570;
	}

	public static boolean isQRGenertedForUser(Users users) {
		return isValidAdultUser(users) && "Y".equalsIgnoreCase(users.getIsQRCodeGenerated());
	}

	public static boolean isValidEmail(String email) {
		if(email == null) {
			return false;
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static boolean isValidPhone(String phone) {
		if(phone == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	public static boolean validatePasswordSize(String password) {
		return password != null && password.length() >= 8;
	}
	
	public static boolean validatePassword(Users users) {
		return true;
	}
	
	public static boolean validateFirstAndLastName(Users users) {
		return true;
	}
	
	public static boolean checkSqlInjections(Users users) {
		return true;
	}
	
	public static boolean validateBirthDate(Users users) {
		return true;
	}
	
	public static boolean validUserType(Users users) {
		return true;
	}
}