package com.carsale.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.carsale.model.User;

@Repository
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(@Param("username") String username);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	User findById(@Param("id") Long id);
	
	@Override
	List<User> findAll();

	@Override
	List<User> findAll(Sort sort);

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	List<User> findAll(Iterable<Long> ids);

}
