package com.sfda.test

import static org.junit.Assert.assertTrue

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.ContextHierarchy
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

import com.sfda.SFDAApplication
import com.sfda.controller.UsersController
import com.sfda.entity.Users
import com.sfda.repository.UsersLoginRepository
import com.sfda.repository.UsersRepository
import com.sfda.service.UsersService

import spock.lang.Specification
import spock.mock.DetachedMockFactory

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SFDAApplication])
class SfdaBehaviroalTest extends Specification {

	@Autowired
	UsersController userController

	@Autowired
	private UsersService testUsersService

	@Autowired
	private UsersRepository usersRepositoryStub

	def setup() {
		userController = new UsersController(testUsersService)
	}

	def "when context is loaded then all expected beans are created"() {
		expect: "the UsersController is created"
		userController
	}

	@Test
	def "Successful registration by a user as DONOR"() {

		given: "The user is ready to get registered with required details."
		Users user = new Users()
		user.setFirstName("TestFirstName")
		user.setLastName("TestLastName")
		user.setIsValidated("Y")
		user.setIsQRCodeGenerated("Y")


		and: "User wants to get registered as a Donor"
		user.setType("FAMILY")

		when: "User enters all the required information in the login form"
		user.setPhone("123-456-7890")
		user.setEmail("test4342@test.com")
		user.setPassword("passWord@123")

		and: "And submits the rquest form on the SFDA Home page"
		ResponseEntity<Users> savedUser = userController.saveUser(user)

		then: "User gets redirected to the Home page"
		assertTrue (savedUser.getBody() != null)
		assertTrue (savedUser.getBody().getToken() != null)
	}
}