package com.carsale.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carsale.CarSaleApplication;
import com.carsale.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CarSaleApplication.class)
@WebAppConfiguration
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		User user = new User();
		user.setUsername("testName");
		user.setPassword("password");
		user = userRepository.save(user);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		User otherUser = userRepository.findByUsername(user.getUsername());
		assertEquals("testName", otherUser.getUsername());
		
		userRepository.delete(otherUser);
	}

	@Test
	public void testFind() throws Exception {
		User user = userRepository.findOne(1L);
		assertEquals("admin", user.getUsername());
	}

}