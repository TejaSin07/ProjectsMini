package in.tejas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.tejas.binding.LoginForm;
import in.tejas.binding.SignUpForm;
import in.tejas.binding.UnlockForm;
import in.tejas.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form,Model model) {
		boolean status = userService.signUp(form);
		if(status) {
			model.addAttribute("succMsg","Account Created Check Your Email");
		}else {
			model.addAttribute("errMsg","Choose unique email ");
		}
		return "signup";
	}
	

	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email,Model model) {
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);
		model.addAttribute("unlock",unlockFormObj);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String UnlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock,Model model) {
		System.out.println(unlock);
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAccount(unlock);
			if(status) {
				model.addAttribute("succMsg","your account unlocked successfully");
			}
			else {
				model.addAttribute("errMsg","given temp pswd is incorrect ,check your email");
			}
		}
		else {
			model.addAttribute("errMsg","new password and confirm password should be same");
		}
		
		return "unlock";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm",new LoginForm());
		return "login";
		
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form,Model model) {
		String status = userService.login(form);
		if(status.contains("success")) {
			//redirect to dashboard 
//			return "redirect:/dashboard";
			
			return "redirect:/dashboard";
		}
		
		model.addAttribute("errMsg",status);

		return "login";
		
	}
	
	
	
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	
	@GetMapping("/dash")
	public String dashboard() {
		return "dashboard";
	}
	
	
	
}
