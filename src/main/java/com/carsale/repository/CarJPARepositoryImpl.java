package com.carsale.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carsale.model.Car;

@Component
public class CarJPARepositoryImpl implements CarJPARepositoryCustom {

	@Autowired
	CarRepository carRepository;

	@Override
	public List<Car> findAllCarsByModelNameCustom(String modelName) {
		return carRepository.findAllCarsByModelName(modelName);
	}

}

