package com.carsale.service;

import java.util.List;

import com.carsale.model.Car;

public interface ICarService {

	List<Car> findAllCarsByModelName(String modelName);
}
