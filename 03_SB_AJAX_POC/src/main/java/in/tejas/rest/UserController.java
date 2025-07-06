package in.tejas.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String load() {
		return "index";
		
	}
	
	@GetMapping("cmsg")
	@ResponseBody
	public String getDropDownMsg(@RequestParam("countryName") String cname) {
		String msg  = "I am going to "+cname+" in next month";  
		return msg;
		
		
	}
	
	@GetMapping("/msg")
	@ResponseBody
	public String getMsg(@RequestParam String name) {
		String msg  = "Hello, " + name;  
		return msg;
		
		
	}

}
