package org.kzn.itis;

import javax.validation.Valid;

import org.kzn.itis.model.BrandManager;
import org.kzn.itis.model.Car;
import org.kzn.itis.model.CarManager;
import org.kzn.itis.validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/new_car.html")
public class CarNewController {
	@Autowired
	CarValidator validator;
	@RequestMapping(method=RequestMethod.GET) 
	public String initializeForm(Model model) {		 
		BrandManager brandManager = new BrandManager();
		model.addAttribute("car", new Car());
		model.addAttribute("brandList", brandManager.getBrandList());
		return "carNew";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(@ModelAttribute("car") @Valid Car car, BindingResult result, ModelMap model) {
		 validator.validate(car, result);
		 if (result.hasErrors()) {
			 return "carNew";
		 }
		 CarManager carManager = new CarManager();
		 carManager.createCar(car);
		 return "redirect:/list_cars.html";
	}
}
