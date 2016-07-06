package com.carsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.carsale.model.Car;

@Repository
@RepositoryRestResource(path="cars")
public interface CarRepository extends JpaRepository<Car, Long>{
	
	List<Car> findAllCarsByModelName(@Param("modelName") String modelName);
	
}
