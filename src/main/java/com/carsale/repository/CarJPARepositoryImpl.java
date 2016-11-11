package com.carsale.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.carsale.model.Car;

@Component
public class CarJPARepositoryImpl implements CarJPARepositoryCustom {

	@Autowired
	CarRepository carRepository;

	@Override
	public Page<Car> findAllCarsByModelNameCustom(String modelName, Pageable pageable) {
		return carRepository.findAllCarsByModelName(modelName, pageable);
	}

}

