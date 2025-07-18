package in.tejas.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tejas.appspro.AppProperties;
import in.tejas.constant.AppConstants;

@RestController
public class MsgRestController {
	
	@Autowired
	private AppProperties appProps;
	
	
	
	@GetMapping("/welcome")
	public  String  getWelcomeMsg() {
//		String msg = "welcome to ashok  it";//if you save value like this then at each time  while changes you need to compile,package and deploy
		
		return appProps.getMessages().get(AppConstants.WELCOME_MSG_KEY);
	}
	
	@GetMapping("/greet")
	public String getGreetMsg() {
		return appProps.getMessages().get(AppConstants.GREET_MSG_KEY);
	}
	
}
