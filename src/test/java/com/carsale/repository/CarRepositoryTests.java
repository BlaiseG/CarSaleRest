package com.carsale.repository;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

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
import com.carsale.model.Car;
import com.carsale.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CarSaleApplication.class)
@WebAppConfiguration
public class CarRepositoryTests {
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Date date = normalize(Calendar.getInstance(), 2010, 05, 11, 12, 51, 52);		
		User user = new User();
		user.setUsername("testName");
		user.setPassword("password");
		user = userRepository.save(user);
		
		Car car = new Car();
		car.setModelName("testBMW");
		car.setPurchaseDate(date);
		car.setUser(user);
		car = carRepository.save(car);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Car otherCar = carRepository.findOne(car.getId());
		assertEquals("testBMW", otherCar.getModelName());
		assertEquals(date, otherCar.getPurchaseDate());
		
		carRepository.delete(otherCar);
		userRepository.delete(user);
	}
	
	private Date normalize(Calendar cal, int year, int month, int date, int hourOfDay, int minute, int second) {
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(year, month, date, hourOfDay, minute, second);
		return cal.getTime();
	}
	
}