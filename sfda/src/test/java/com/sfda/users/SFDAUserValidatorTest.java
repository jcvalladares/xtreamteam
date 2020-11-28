package com.sfda.users;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.sfda.entity.Users;
import com.sfda.util.UserDetailsValidator;

class SFDAUserValidatorTest {

	@Test
    public void junitWorksProperly(){
		assertTrue(true);
    }

	@Test
    public void testValidateIfTrustedDonor(){
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		donor.setType("DONOR");
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidDonor(donor));
    }
	
	@Test
    public void testValidateIfAdultUser() throws ParseException{
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setLastName("LN_Test1");
		Date date = new java.sql.Date(new SimpleDateFormat("MM-dd-yyyy").parse( "10-10-1975").getTime());
		user.setBirthDate(date);
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidAdultUser(user));
    }
	
	@Test
    public void testVaidateIfUserRegistrationComplete() throws ParseException{
		Users user = new Users();
		user.setFirstName("FN_Test1");
		user.setLastName("LN_Test1");
		user.setIsQRCodeGenerated("Y");
		Date date = new java.sql.Date(new SimpleDateFormat("MM-dd-yyyy").parse( "10-10-1975").getTime());
		user.setBirthDate(date);
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isQRGenertedForUser(user));
    }
	
}