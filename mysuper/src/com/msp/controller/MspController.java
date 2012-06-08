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
	
	@RequestMapping(value="/velocity/view1.html")
	public String toVelocity(HttpServletRequest req, Model model) {
		
		model.addAttribute("currTime", System.currentTimeMillis());
		model.addAttribute("contextPath", req.getContextPath());
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
        sb.append("['3m Co',                               71.72, 0.02,  0.03,  '9/1 12:00am'],");
        sb.append("['Alcoa Inc',                           29.01, 0.42,  1.47,  '9/1 12:00am'],");
		sb.append("['Altria Group Inc',                    83.81, 0.28,  0.34,  '9/1 12:00am'],");
		sb.append("['American Express Company',            52.55, 0.01,  0.02,  '9/1 12:00am'],");
		sb.append("['American International Group, Inc.',  64.13, 0.31,  0.49,  '9/1 12:00am'],");
		sb.append("['AT&T Inc.',                           31.61, -0.48, -1.54, '9/1 12:00am'],");
		sb.append("['Boeing Co.',                          75.43, 0.53,  0.71,  '9/1 12:00am']");
		sb.append("]");
		
		model.addAttribute("data", sb);
		System.out.println("#########contextPath = " + req.getContextPath());
		return "welcome";
	}
	@RequestMapping(value="/velocity/view2.html")
	public String toView2(HttpServletRequest req, Model model) {
		
		model.addAttribute("currTime", System.currentTimeMillis());
		System.out.println("############req.getPathInfo() = " + req.getPathInfo());
		model.addAttribute("data", req.getPathInfo());
		
		return "view2";
	}
}
