package org.kzn.itis;
import javax.validation.Valid;

import org.kzn.itis.model.Brand;
import org.kzn.itis.model.BrandManager;
import org.kzn.itis.model.Car;
import org.kzn.itis.model.CarManager;
import org.kzn.itis.validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] {"brand"});
	}
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(@RequestParam String brand, @ModelAttribute("car") Car car, BindingResult result, Model model) {
		BrandManager brandManager = new BrandManager();
		System.out.println(brand);
		car.setBrand(brandManager.getBrandById(Long.parseLong(brand)));
		validator.validate(car, result);
		if (result.hasErrors()) {
			return "carNew";
		}
		
		CarManager carManager = new CarManager();
		carManager.createCar(car);
		return "redirect:/list_cars.html";
	}
}
