package com.carsale.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
	@RequestMapping("/")
	public String helloWorld(Model model) {
		return "index";
	}
}
