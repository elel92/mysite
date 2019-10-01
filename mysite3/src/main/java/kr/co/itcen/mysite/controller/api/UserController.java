package kr.co.itcen.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.dto.JSonResult;
import kr.co.itcen.mysite.service.UserService;

@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSonResult checkEmail(@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		boolean exist = userService.existUser(email);
		
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("result", "success");
		//map.put("data", exist);
		
		return JSonResult.success(exist);
	}
}
