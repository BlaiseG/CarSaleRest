package com.carsale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carsale.model.Car;
import com.carsale.repository.CarRepository;

@Component
public class CarService implements ICarService {

	@Autowired
	CarRepository carRepository;

	@Override
	public List<Car> findAllCarsByModelName(String modelName) {
		return carRepository.findAllCarsByModelName(modelName);
	}

}
