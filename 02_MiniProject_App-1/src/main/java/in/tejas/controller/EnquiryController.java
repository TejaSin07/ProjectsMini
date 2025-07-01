package in.tejas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
	
	@GetMapping("/dashboard")
	public String dashboardPage() {
		//logic to fetch data for dashboard
		System.out.println("dashboard method called");
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage() {
		return "enquiry";
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiries() {
		return "view-enquiries";
	}
	
	
	
}
