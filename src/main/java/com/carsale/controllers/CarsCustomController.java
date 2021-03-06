package com.carsale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carsale.model.Car;
import com.carsale.repository.CarJPARepository;
import com.carsale.repository.CarRepository;

@BasePathAwareController
@RequestMapping("/cars")
public class CarsCustomController implements ResourceProcessor<RepositorySearchesResource> {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarJPARepository carJPARepository;
    @Autowired
    private PagedResourcesAssembler<Car> pagedAssembler;

	@RequestMapping(value = "search/findBMW", method = RequestMethod.GET)
    public ResponseEntity<Resources<Resource<Car>>> findBMW(Car filterCriteria, Pageable pageable, PersistentEntityResourceAssembler entityAssembler) {

        Page<Car> bmw = carRepository.findByModelName("BMW", pageable);
        // Adds the "_self" link to findBMW endpoint
        /**
         * "_links": {
            "self": {
            "href": "http://localhost:8080/api/cars/search/findBMW"
         },
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        PagedResources<Resource<Car>> resource = pagedAssembler.toResource(bmw,
                (ResourceAssembler) entityAssembler);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    /*
        Adds the findBMW to: http://localhost:8080/api/cars/search as
       "carsale:findBMW": {
         "href": "http://localhost:8080/api/cars/search/findBMW"
        }
    },
     */
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
