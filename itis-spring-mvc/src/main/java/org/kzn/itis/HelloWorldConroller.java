package org.kzn.itis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldConroller implements Controller {
	public ModelAndView handleRequest(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String aMessage = "Hello, Rishat!";
		ModelAndView modelAndView = new ModelAndView("hello_world");
		modelAndView.addObject("message", aMessage);
		return modelAndView;
	}
}
