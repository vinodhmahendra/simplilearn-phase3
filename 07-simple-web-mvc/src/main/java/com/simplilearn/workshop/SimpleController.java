package com.simplilearn.workshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/showMessage.html")
public class SimpleController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView helloworld() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("showMessage"); // logical view name
		modelAndView.addObject("message", "Spring  MVC Web Application With XML and Annotations");
		
		return modelAndView;
	}

}
