package org.kzn.itis;

import org.kzn.itis.model.Car;
import org.kzn.itis.model.CarManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/list_cars.html")
public class CarListController {
	@RequestMapping(method=RequestMethod.GET) 
	public String initializeForm(Model model) {
		model.addAttribute("carList", new CarManager().getCarList());
		return "carList";
	}
}
