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
		model.addAttribute("loginBean",new loginBean());
		return "login";
	}
}
