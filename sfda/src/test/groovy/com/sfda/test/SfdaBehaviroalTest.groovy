package com.sfda.test

import static org.junit.Assert.assertTrue

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration

import com.sfda.controller.UsersController
import com.sfda.entity.Users
import com.sfda.repository.UsersLoginRepository
import com.sfda.repository.UsersRepository
import com.sfda.service.UsersService

import spock.lang.Ignore
import spock.lang.Specification
import spock.mock.DetachedMockFactory

@ContextConfiguration(classes = SfdaBehaviroalTest.SFDATestConfiguration)
class SfdaBehaviroalTest extends Specification {

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

	@Ignore
	def "Successful registration by a user as DONOR"() {

		given: "The user is ready to get registered with required details."
		Users user = new Users()
		user.setFirstName("TestFirstName")
		user.setLastName("TestLastName")
		user.setEmail("test4342@test.com")
		user.setPassword("passWord@123")
		user.setIsValidated("Y")
		user.setIsQRCodeGenerated("Y")
		user.setPhone("1234567890")

		and: "User wants to get registered as a Donor"
		user.setType("FAMILY")

		and: "Login and UserLogin repository are able to save the user details to data store"
		usersRepositoryStub.save(user) >> user

		when: "User enters all the required information in the login form"

		and: "And submits the rquest form on the SFDA Home page"
		ResponseEntity<Users> savedUser = userController.saveUser(user)

		then: "User gets redirected to the Home page"
		assertTrue (savedUser.getBody().getToken() != null)
	}

	@TestConfiguration
	static class SFDATestConfiguration {

		DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

		@Bean
		public UsersService testUsersService() {
			return new UsersService(usersRepositoryStub(), usersLoginRepositoryStub())
		}

		@Bean
		public UsersRepository usersRepositoryStub () {
			return detachedMockFactory.Stub(UsersRepository)
		}

		public UsersLoginRepository usersLoginRepositoryStub () {
			return detachedMockFactory.Stub(UsersLoginRepository)
		}
	}
}