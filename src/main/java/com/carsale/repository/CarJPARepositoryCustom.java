package com.carsale.repository;

import java.util.List;

import com.carsale.model.Car;

public interface CarJPARepositoryCustom {
	List<Car> findAllCarsByModelNameCustom(String modelName);
}
