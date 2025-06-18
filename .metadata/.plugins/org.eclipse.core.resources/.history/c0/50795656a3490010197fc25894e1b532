package in.tejas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.tejas.service.CitizenService;

@Controller
public class RestController {
	
	@Autowired 
	private CitizenService service;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
}
