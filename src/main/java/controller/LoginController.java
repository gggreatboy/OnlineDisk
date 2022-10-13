package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.expr.NewArray;
import pojo.loginBean;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String Login(Model model) {
		loginBean lo =new loginBean();
		lo.setUname("123");
		lo.setUpwd("123");
		model.addAttribute("loginBean",lo);
		return "login";
	}
	@RequestMapping("/register")
	public String Register(Model model) {
		model.addAttribute("loginBean",new loginBean());
		return "register";
	}
}
