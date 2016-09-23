package com.carsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carsale.model.Car;

@Repository
public interface CarJPARepository extends JpaRepository<Car, Long>, CarJPARepositoryCustom {

}
