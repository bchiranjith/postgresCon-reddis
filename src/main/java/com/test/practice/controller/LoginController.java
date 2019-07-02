package com.test.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.practice.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@Autowired
	PostgresqlConnection con;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
		
		boolean isValidUser = service.validateUser(name, password);
		
		if (!isValidUser) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		con.getAllThings();
		//con.getThingIdByName();
		model.put("name", name);
		model.put("password", password);
		
		return "welcome";
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String showTestPage(ModelMap model){
		
		
		//con.getAllThings();
		//con.getThingIdByName();
		
		
		return "test";
	}
	@RequestMapping(value="/test", method = RequestMethod.POST)
	public String showTestId(ModelMap model, @RequestParam String deviceName){
		
		
		System.out.println(" Device Name "+deviceName);
		String deviceId = null;
		deviceId = con.getThingIdByName(deviceName);
		
		if(null!=deviceId ) {
			model.put("DeviceId", "DeviceId is: "+deviceId);
		}
		else {
			model.put("errorMessage", "No Device named: "+ deviceName);

		}
		
		return "test";
	}

}
