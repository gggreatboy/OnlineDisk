package controller;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.expr.NewArray;
import pojo.User;
import service.Link;

@Controller
public class LoginController {
	@RequestMapping({"/login","/"})
	public String Login(Model model) {
		User lo =new User("123","123");

		model.addAttribute("User",lo);
		return "login";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping("/loginconfirm")
	public String Loginconfirm(@ModelAttribute User user,HttpSession session,HttpServletRequest request,Model model) {
		String username= user.getUsername();
		String userpwd=  user.getUserpwd();
		model.addAttribute("username",username);
		Link link=new Link();
		try {
			link.findUser(username, userpwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String kongjian=username;
		String path = request.getServletContext().getRealPath("/"+kongjian+"/");
		String sharepath = request.getServletContext().getRealPath("/shareplacejust/");
		File targetDir = new File(path);
		File fileListorigin[] =targetDir.listFiles();
		long store=0;
		
		if(fileListorigin!=null) {
		for(int i =0;i<fileListorigin.length;i=i+1) {
			store+=(fileListorigin[i].length())/1024;
		}
		session.setAttribute("Store", store);
		}
		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir);
        session.setAttribute("filepathnow",targetDir);
        model.addAttribute("username",kongjian);
        session.setAttribute("username", kongjian);
        session.setAttribute("pathorigin1", path);
        request.getSession().getServletContext().setAttribute("userpath","/"+username);
        request.getSession().getServletContext().setAttribute("sharepath",sharepath);
		
		return "index";
	}
	
	
	@RequestMapping("/toregister")
	public String Register(Model model) {
		model.addAttribute("User",new User("",""));
		return "register";
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping("/registerconfirm")
	public String Registerconfirm(@ModelAttribute User user,HttpSession session,HttpServletRequest request,Model model) {

        String msg="";
		Link link=new Link();
		try {
			link.addUser(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("User",user);
		return "login";
	}
}
