package com.sfda.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

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
		if (email == null) {
			return false;
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static boolean isValidPhone(String phone) {
		if (phone == null) {
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
		if (validatePasswordSize(users.getPassword())) {
			String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(users.getPassword());

			return m.matches();
		}
		return false;
	}

	public static boolean validateFirstAndLastName(Users users) {
		if (StringUtils.isNotEmpty(users.getFirstName()) && StringUtils.isNotEmpty(users.getLastName())
				&& users.getFirstName().length() <= 30 && users.getLastName().length() <= 30) {
			return true;
		}
		return false;
	}

	public static boolean checkSqlInjections(Users users) {
		List<String> sqls = Arrays.asList("select", "update", "execute", "delete");

		return users.getFirstName() != null && users.getLastName() != null && users.getMiddleName() != null
				&& users.getEmail() != null && users.getPhone() != null
				&& (sqls.contains(users.getFirstName().toLowerCase())
						|| sqls.contains(users.getFirstName().toLowerCase())
						|| sqls.contains(users.getMiddleName().toLowerCase())
						|| sqls.contains(users.getEmail().toLowerCase())
						|| sqls.contains(users.getPhone().toLowerCase()));
	}

	public static boolean validateBirthDate(Users users) {
		return users.getBirthDate() instanceof Date;
	}

	public static boolean validateRequiredFieldsAreNotEmpty(Users users) {
		return StringUtils.isNotEmpty(users.getFirstName()) && StringUtils.isNotEmpty(users.getLastName())
				&& users.getBirthDate() != null && StringUtils.isNotEmpty(users.getPassword())
				&& StringUtils.isNotEmpty(users.getEmail()) && StringUtils.isNotEmpty(users.getPhone());
	}

	public static boolean validateMiddleName(Users users) {
		return StringUtils.isNotEmpty(users.getFirstName()) && StringUtils.isNotEmpty(users.getLastName());
	}

	public static boolean validateUserType(Users users) {
		return users.getType() != null && (users.getType().equals(UserType.DONOR.name())
				|| users.getType().equals(UserType.FAMILY.name()) || users.getType().equals(UserType.VENDOR.name())
				|| users.getType().equals(UserType.NGO.name()) || users.getType().equals(UserType.USER.name()));
	}

	public static Users validateAndUpdateAge(Users users) {
		if (validateBirthDate(users)) {
			LocalDate dt = LocalDate.of(users.getBirthDate().getYear(), users.getBirthDate().getMonth(),
					users.getBirthDate().getDay());
			LocalDate now = LocalDate.now();
			Period p = Period.between(dt, now);
			users.setAge(p.getYears());
		}
		return null;
	}

	public static Users generateToken(Users users) {
		users.setToken(UUID.randomUUID().toString());
		return users;
	}
}