package in.tejas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.tejas.binding.DashBoardResponse;
import in.tejas.binding.EnquiryForm;
import in.tejas.binding.EnquirySearchFilter;
import in.tejas.entity.StudentEnqEntity;
import in.tejas.service.EnquiryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryService enqService;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		//logic to fetch data for dashboard
		Integer userId = (Integer) session.getAttribute("userId");
		DashBoardResponse dashboardData = enqService.getDashboardData(userId);
		model.addAttribute("dashboardData",dashboardData);
	
		return "dashboard";
	}
	
	
	
	private void initForm(Model model) {
		List<String> courses = enqService.getCourseName();
		
		List<String> enqStatuses = enqService.getEnqStatus();
		
		EnquiryForm formObj = new EnquiryForm();
		
		model.addAttribute("courseNames",courses);
		model.addAttribute("statusNames",enqStatuses);
		model.addAttribute("formObj",formObj);
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiriesPage(Model model) {
	    initForm(model); // already adds courseNames, statusNames, formObj

	    model.addAttribute("modeList", List.of("Online", "Offline"));
	    model.addAttribute("enqStatusList", enqService.getEnqStatus());
	    model.addAttribute("courseNames", enqService.getCourseName());

	    List<StudentEnqEntity> enquiries = enqService.getEnquries();
	    model.addAttribute("enquiries", enquiries);

	    return "view-enquiries";
	}

	@GetMapping("/enquiry")
	public String loadEnquiryPage(Model model) {
	    model.addAttribute("enqForm", new EnquiryForm());

	    // Inject dropdown values
	    model.addAttribute("courseNames", enqService.getCourseName());
	    model.addAttribute("enqStatusList", enqService.getEnqStatus());
	    model.addAttribute("modeList", List.of("Online", "Offline"));

	    return "enquiry";  // HTML filename (enquiry-form.html)
	}

	@PostMapping("/enquiry")
	public String handleFormSubmit(@ModelAttribute("enqForm") EnquiryForm form) {
	    String status = enqService.upsertEnquiry(form);
	    if ("success".equals(status)) {
	        return "redirect:/enquiries"; // or wherever you list enquiries
	    } else {
	        return "enquiry"; // show same page in case of failure
	    }
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnq(@RequestParam String cname,
	                             @RequestParam String status,
	                             @RequestParam String mode,
	                             Model model) {
	    EnquirySearchFilter criteria = new EnquirySearchFilter();
	    criteria.setCourseName(cname);
	    criteria.setClassMode(mode);
	    criteria.setEnqStatus(status);

	    Integer userId = (Integer)session.getAttribute("userId");

	    List<StudentEnqEntity> filteredEnq = enqService.getFilteredEnquiries(criteria, userId);
	    model.addAttribute("enquiries", filteredEnq);

	    // 👇 IMPORTANT: this returns only a table fragment!
	    return "filter-enquiries-page :: tableContent";
	}

}
//
//	@GetMapping("/filter-enquiries")
//	public String getFilteredEnq(@RequestParam(name = "cname", required = false) String cname,
//	                             @RequestParam(name = "status", required = false) String status,
//	                             @RequestParam(name = "mode", required = false) String mode,
//	                             Model model) {
//
//	    EnquirySearchFilter criteria = new EnquirySearchFilter();
//	    criteria.setCourseName(cname);
//	    criteria.setClassMode(mode);
//	    criteria.setEnqStatus(status);
//
//	    System.out.println("Filter Criteria: " + criteria);
//
//	    List<StudentEnqEntity> filteredEnquiries = enqService.getFilteredEnquiries(criteria);
//	    model.addAttribute("enquiries", filteredEnquiries);
//
//	    // Include required dropdowns again for Thymeleaf rendering
//	    model.addAttribute("modeList", List.of("Online", "Offline"));
//	    model.addAttribute("enqStatusList", enqService.getEnqStatus());
//	    model.addAttribute("courseNames", enqService.getCourseName());
//
//	    return "view-enquiries";
//	}



	
	
	

