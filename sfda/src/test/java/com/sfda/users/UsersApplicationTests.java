package com.sfda.users;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import com.sfda.controller.LoginController;
import com.sfda.controller.UsersController;
import com.sfda.entity.Users;
import com.sfda.repository.UsersRepository;
import com.sfda.service.UsersService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	LoginController loginController;

	@Autowired
	UsersController userController;

	@Test
    public void junitWorksProperly(){
        assert true;
    }
	
	@Test
	public void testSuccessfulUserSaveFromAdminScreenScenario() {
		Users user1 = new Users();
		user1.setId(777);
		user1.setFirstName("SFDA_Test1");
		user1.setLastName("SFDA_Test_LastName1");
		user1.setEmail("SFDA_Test1@test.com");
		user1.setType("NGO");
		user1.setIsValidated("Y");
		user1.setIsQRCodeGenerated("Y");
		ResponseEntity<?> response = userController.saveUser(user1);
		assert response.getStatusCode() == HttpStatus.OK;
	}

	@Test
	public void testUnSuccessfulUserSaveFromAdminScreenScenario() {
		Users user = new Users();
		user.setFirstName("TestFirst");//only setting one of the required field
		Exception exception = assertThrows(TransactionSystemException.class, () -> {
			userController.saveUser(user);
		});
		String expectedMessage = "Could not commit JPA transaction";
		String actualMessage = exception.getMessage();
		assert actualMessage.contains(expectedMessage) == true;
	}

	@Test
	public void testGetAllUsersScenario() {
		ResponseEntity<?> response = userController.listUsers();
		assert response.getStatusCode() == HttpStatus.OK;
	}
	
	@Test
	public void testRegisterUserSuccessfulScenario() {
		Users user = new Users();
		user.setFirstName("TestSecond32");
		user.setLastName("TestLast232");
		user.setEmail("test4342@test.com");
		user.setType("DONOR");
		user.setIsValidated("Y");
		user.setIsQRCodeGenerated("Y");
		ResponseEntity<?> response = loginController.registerUser(user);
		assert response.getStatusCode() == HttpStatus.OK;
	}
	
	@Test
	public void testUnSuccessfulRegisterUserScenario() {
		Users user = new Users();//not setting the required fields
		Exception exception = assertThrows(TransactionSystemException.class, () -> {
			loginController.registerUser(user);
		});
		String expectedMessage = "Could not commit JPA transaction";
		String actualMessage = exception.getMessage();
		assert actualMessage.contains(expectedMessage) == true;
	}
	
	@Test
	public void testSuccessfulUserLoginScenario() {
		Users user = new Users();
		user.setFirstName("TestSecond3");
		user.setLastName("TestLast24");
		user.setEmail("test332@test.com");
		Users response = loginController.loginUser("abc@xyz.com", "abcd");
		assertTrue(response != null);
	}
	
	@Test
	public void testUnSuccessfulUserLoginScenario() {
		Users response = loginController.loginUser(null, "abcd");
		assertTrue(response != null);
	}
	
	@Test
	public void testExceptionScenarioWhenDatabaseIsDown() {
		UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
		UsersService service = new UsersService(usersRepository);
		LoginController mockLoginController = new LoginController(service);
	    Mockito.when(usersRepository.save(Mockito.any())).thenThrow(new RuntimeException("Database is down."));
	    Users user = new Users();
	    
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	mockLoginController.registerUser(user);
		});
		String expectedMessage = "Database is down.";
		String actualMessage = exception.getMessage();
		assert actualMessage.contains(expectedMessage) == true;
	}
	
	@Test
	public void testResetPasswordLinkSuccessfulScenario() {
		Users user = new Users();
		user.setEmail("test2@test.com");
		ResponseEntity<?> response = loginController.resetPassword(user);
		assert response.getStatusCode() == HttpStatus.OK;
	}
	
	@Test
	public void testResetPasswordLinkExceptionScenario() {
		UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
		UsersService service = new UsersService(usersRepository);
		LoginController mockLoginController = new LoginController(service);
	    Mockito.when(usersRepository.findAll()).thenThrow(new RuntimeException("Email address not found in our system."));
	    Users user = new Users();
	    
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	mockLoginController.resetPassword(user);
		});
		String expectedMessage = "mail address not found in our system.";
		String actualMessage = exception.getMessage();
		assert actualMessage.contains(expectedMessage) == true;
	}
}