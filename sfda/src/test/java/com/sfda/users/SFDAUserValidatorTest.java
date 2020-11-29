package com.sfda.users;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.sfda.entity.Users;
import com.sfda.util.UserDetailsValidator;

class SFDAUserValidatorTest {

	@Test
	public void junitWorksProperly() {
		assertTrue(true);
	}

	@Test
	public void testValidateIfTrustedDonor() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		assertTrue(UserDetailsValidator.isValidDonor(donor));
	}

	@Test
	public void testValidateIfAdultUser() throws ParseException {
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setLastName("LN_Test1");
		Date date = new java.sql.Date(new SimpleDateFormat("MM-dd-yyyy").parse("10-10-1975").getTime());
		user.setBirthDate(date);
		assertTrue(UserDetailsValidator.isValidAdultUser(user));
	}

	@Test
	public void testValidateIfUserRegistrationComplete() throws ParseException {
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setLastName("LN_Test1");
		user.setIsQRCodeGenerated("Y");
		Date date = new java.sql.Date(new SimpleDateFormat("MM-dd-yyyy").parse("10-10-1975").getTime());
		user.setBirthDate(date);
		assertTrue(UserDetailsValidator.isQRGenertedForUser(user));
	}

	@Test
	public void testValidateIncorrectUserEmail() {
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setEmail("aaabbb");
		assertTrue(!UserDetailsValidator.isValidEmail(user.getEmail()));
	}

	@Test
	public void testVaidateIncorrectUserPhone() {
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setLastName("LN_Test1");
		user.setPhone("abcd5998");
		assertTrue(!UserDetailsValidator.isValidPhone(user.getPhone()));
	}

	@Test
	public void testValidatePasswordLessThanMinRequired(){
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setPassword("abc123");
		assertTrue(!UserDetailsValidator.validatePasswordSize(user.getPassword()));
	}
}