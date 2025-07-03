package in.tejas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tejas.binding.LoginForm;
import in.tejas.binding.SignUpForm;
import in.tejas.binding.UnlockForm;
import in.tejas.entity.UserDtlsEntity;
import in.tejas.repo.UserDtlsRepo;
import in.tejas.utils.EmailsUtils;
import in.tejas.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private EmailsUtils emailUtils;

	@Override
	public boolean signUp(SignUpForm form) {
		
		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
		if(user != null)return false;
		// copy data from binding object to entity obj
		UserDtlsEntity entity = new UserDtlsEntity();
		
		// TODO generate random password and set to object
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);
		
		// set account status as locked
		entity.setAccStatus("LOCKED");
	    entity.setName(form.getName());
	    entity.setEmail(form.getEmail());
	    entity.setPhno(form.getPhno());
		// insert record;
		userDtlsRepo.save(entity);
	    
		// send email to unlock the account
		String to = form.getEmail();
		String subject = "Unlock Your Account |AshokIt";
		StringBuffer body = new StringBuffer();
		body.append("<h1>Use below temporary password to unlock your account</h1>");
		body.append("Temporary pwd:" + tempPwd);
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to unlock your account</a>");
		emailUtils.sendEmail(to,subject,body.toString());
		
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		// TODO Auto-generated method stub
		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		if(entity.getPwd().equals(form.getTempPwd())){
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("Unlocked");
			userDtlsRepo.save(entity);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String login(LoginForm form) {
		// TODO Auto-generated method stub
		UserDtlsEntity entity = userDtlsRepo.findByEmailAndPwd(form.getEmail(),form.getPwd());
		if(entity == null) {
			return "Invalid Credentials";
		}
		if(entity.getAccStatus().equals("LOCKED")) {
			return "YOUR ACCOUNT IS LOCKED";
		}
		return "success";
	}

	@Override
	public boolean forgotPwd(String email) {
		// TODO Auto-generated method stub
		//check record present in db or not
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		//if record is not available send false
		if(entity == null) {
			return false;
		}
		//if record available send pwd to email and send true
		String subject = "Recover Pasword";
		String body = "Your Pwd :: "+entity.getPwd();
		emailUtils.sendEmail(email, subject, body);
		return true;
	}

}
