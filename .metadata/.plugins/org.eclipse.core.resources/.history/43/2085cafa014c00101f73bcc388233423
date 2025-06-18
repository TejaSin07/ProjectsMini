package in.tejas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.tejas.entity.CitizenPlan;
import in.tejas.request.SearchRequest;
import in.tejas.service.CitizenService;

@Controller
public class RestController {
	
	@Autowired 
	private CitizenService service;
	 
	/*this method is used to load index page
	  * @param model
	  * @return string
	  */
	@GetMapping("/")
	public String indexPage(Model model) {
		
		
		model.addAttribute("search",new SearchRequest());  //we want only one time for more info L-18:1hr:10min
//		model.addAttribute("names",service.getPlanName());
//		model.addAttribute("status",service.getPlanStatuses());
		//we put all this three method inside the init method
		
		init(model);
		
		return "index";
	}

	private void init(Model model) {
//		model.addAttribute("search",new SearchRequest());
		model.addAttribute("names",service.getPlanName());
		model.addAttribute("status",service.getPlanStatuses());
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans",plans);
		init(model);
		return "index";
		
	}
}
