package in.tejas.service;

import in.tejas.binding.LoginForm;
import in.tejas.binding.SignUpForm;
import in.tejas.binding.UnlockForm;

public interface UserService {
	
	
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String login(LoginForm form);
	
	public boolean forgotPwd(String email);
}

