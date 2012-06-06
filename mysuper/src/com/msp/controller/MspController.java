package com.msp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MspController {
	
	@RequestMapping(value="/")
	public String home() {
		System.out.println("MspController: passing through...");
		return "index";
	}
	
	@RequestMapping(value="/velocity/")
	public String toVelocity(HttpServletRequest req, Model model) {
		
		model.addAttribute("currTime", System.currentTimeMillis());
		model.addAttribute("contextPath", req.getContextPath());
		System.out.println("#########contextPath = " + req.getContextPath());
		return "welcome";
	}
}
