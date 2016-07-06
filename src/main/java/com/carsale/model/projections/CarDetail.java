package com.carsale.model.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.carsale.model.Car;
import com.carsale.model.User;

@Projection(name="carDetail", types={User.class})
public interface CarDetail {
	@Value("#{target.username}")
	String getFullUserName();
	
	@Value("#{target.cars}")
	Car getMyCustomCars();
	
	Car getCars();
}
