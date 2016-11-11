package com.carsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.carsale.model.Car;

@Repository
@RepositoryRestResource(path="cars")
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
	Page<Car> findByModelName(@Param("modelName") String modelName, Pageable pageable);
	Page<Car> findAllCarsByModelName(@Param("modelName") String modelName, Pageable pageable);

	@Override
	@PreAuthorize("HAS_ROLE('ADMIN')")
	Car save(Car entity);

	@Override
	@PreAuthorize("HAS_ROLE('ADMIN')")
	void delete(Car entity);
}
