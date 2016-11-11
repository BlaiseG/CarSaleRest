package com.carsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carsale.model.Car;

public interface CarJPARepositoryCustom {
	Page<Car> findAllCarsByModelNameCustom(String modelName, Pageable pageable);
}
