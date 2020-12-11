package com.sfda.users;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.sfda.entity.Users;
import com.sfda.util.UserDetailsValidator;

public class UserRegistrationTest {
	
	@Test
	public void junitWorksProperly() {
		assertTrue(true);
	}
	
	@Test
	public void testValidPassword() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		assertTrue(UserDetailsValidator.validatePassword(donor));
	}
	
	@Test
	public void testValidFirstNameLastName() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		assertTrue(UserDetailsValidator.validateFirstAndLastName(donor));
	}
	
	@Test
	public void testSqlInjections() {
		Users donor = new Users();
		donor.setFirstName("Select * from users;");
		donor.setMiddleName("mm");
		donor.setLastName("Update");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		donor.setEmail("ss@ss.com");
		donor.setPhone("1234567890");
		assertTrue(UserDetailsValidator.checkSqlInjections(donor));
	}
	
	@Test
	public void testValidBirthDate() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setBirthDate(new Date(2000, 10, 10));
		donor.setPassword("t3stPassw0rd@1");
		assertTrue(UserDetailsValidator.validateBirthDate(donor));
	}
	
	@Test
	public void testValidMiddleName() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setMiddleName("MN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		assertTrue(UserDetailsValidator.validateMiddleName(donor));
	}
	
	@Test
	public void testValidUserTypeISelected() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		assertTrue(UserDetailsValidator.validateUserType(donor));
	}

	@Test
	public void testValidAgeCalculation() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date());
		c.add(Calendar.YEAR, -20);
		donor.setBirthDate(new Date(c.getTime().getTime()));
		assertTrue(UserDetailsValidator.validateAndUpdateAge(donor).getAge() == 20);
	}
	
	@Test
	public void testIfAllRequiredFieldsAreNotEmpty() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		donor.setEmail("dd@dd.com");
		donor.setPhone("1234567890");
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date());
		c.add(Calendar.YEAR, -20);
		donor.setBirthDate(new Date(c.getTime().getTime()));
		assertTrue(UserDetailsValidator.validateRequiredFieldsAreNotEmpty(donor));
	}
	
	@Test
	public void testCheckIfUserIs18YearsOld() {
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		donor.setPassword("t3stPassw0rd@1");
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date());
		c.add(Calendar.YEAR, -40);
		donor.setBirthDate(new Date(c.getTime().getTime()));
		assertTrue(UserDetailsValidator.validateAndUpdateAge(donor).getAge() > 18);
	}
	
	@Test
	public void testTokenIsGenerated() {
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setPassword("abc123");
		assertTrue(StringUtils.isNotEmpty(UserDetailsValidator.generateToken(user).getToken()));
	}
}