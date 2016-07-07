package com.carsale.controllers;

import com.carsale.repository.CarJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carsale.model.Car;
import com.carsale.repository.CarRepository;

@RepositoryRestController
@RequestMapping("/cars")
public class CarsCustomController implements ResourceProcessor<RepositorySearchesResource> {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PagedResourcesAssembler<Car> pagedAssembler;

	@RequestMapping(value = "search/findBMW", method = RequestMethod.GET)
    public ResponseEntity<Resources<Resource<Car>>> findBMW(Car filterCriteria, Pageable pageable, PersistentEntityResourceAssembler entityAssembler) {

        Page<Car> bmw = carRepository.findByModelName("BMW", pageable);
        @SuppressWarnings({ "unchecked", "rawtypes" })
        PagedResources<Resource<Car>> resource = pagedAssembler.toResource(bmw,
                (ResourceAssembler) entityAssembler);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    @Override
    public RepositorySearchesResource process(RepositorySearchesResource resource) {
        if(Car.class.equals(resource.getDomainType())) {
            final String search = resource.getId().getHref();
            final Link findFullTextFuzzy = new Link(search + "/findBMW").withRel("findBMW");
            resource.add(findFullTextFuzzy);
        }
        return resource;
    }
}
