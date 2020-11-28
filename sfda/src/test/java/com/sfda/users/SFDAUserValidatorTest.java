package com.sfda.users;

import static org.junit.Assert.assertTrue;
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
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidDonor(donor));
    }
	
	@Test
    public void testValidateIfAdultUser(){
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidDonor(donor));
    }
	
	@Test
    public void testVaidateIfUserRegistrationComplete(){
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidDonor(donor));
    }
	
	@Test
    public void testValidateIfUserIsHeadOfFamily(){
		Users donor = new Users();
		donor.setFirstName("FN_Test1");
		donor.setLastName("LN_Test1");
		donor.setIsValidated("Y");
		UserDetailsValidator validator = new UserDetailsValidator();
		assertTrue(validator.isValidDonor(donor));
    }
}