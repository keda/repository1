package com.msp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MspController {
	
	@RequestMapping(value="/")
	public String home() {
		System.out.println("MspController: passing through...");
		return "index.jsp";
	}
}
