package com.macrosoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {
	
	@RequestMapping(path="/employeehome", method=RequestMethod.GET)
	public String goHome(){
		return "employee";
	}

}
