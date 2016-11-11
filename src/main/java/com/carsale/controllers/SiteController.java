package com.carsale.controllers;

import com.carsale.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

	@Autowired
	private CarRepository carRepository;

	@RequestMapping("/")
	public String helloWorld(Model model) {

		return "index";
	}
}
