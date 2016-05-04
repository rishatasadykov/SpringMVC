package org.kzn.itis.validator;

import java.math.BigDecimal;
import java.util.List;

import org.kzn.itis.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {

	public boolean supports(Class aClass) {
		return Car.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Car car = (Car) obj;
		List<ObjectError> l = errors.getAllErrors();
		for (ObjectError oe: l) {
			System.out.println(oe.toString());
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required", "Required field");
		if (!errors.hasFieldErrors("price")) {

		  	if (car.getPrice().equals(BigDecimal.ZERO)) {

		        errors.rejectValue("price", "not_zero", "Can't be free!");
		  		
		  	}

		}
		
	}
	
}
