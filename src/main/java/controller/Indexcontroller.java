package controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Indexcontroller {
	@RequestMapping("/backtoindex")  //根请求直接转到index.html
	public String tobackindex(HttpSession session,HttpServletRequest request,Model model) {
		String kongjian=(String)session.getAttribute("username");
		String path = request.getServletContext().getRealPath("/"+kongjian+"/");
		
		File targetDir = new File(path);
		File fileListorigin[] =targetDir.listFiles();
		long store=0;
		for(int i =0;i<fileListorigin.length;i=i+1) {
			store+=(fileListorigin[i].length())/1024;
		}
		session.setAttribute("Store", store);
		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir);
        session.setAttribute("filepathnow",targetDir);
        model.addAttribute("username",kongjian);
        session.setAttribute("username", kongjian);
        session.setAttribute("pathorigin1", path);
		return "index";
	}
	
	@RequestMapping("/test") 
	public String test(HttpSession session,HttpServletRequest request,Model model) {

		return "login";
	}
	
}
