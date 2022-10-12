package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.Modifyfilename;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Modifyfilecontroller {
	@RequestMapping("/modify")  //修改文件名请求
	public String toModify(HttpSession session,HttpServletRequest request, 
			@RequestParam("filename") String filename,
			@RequestParam("filepath") String filepath,Model model) {
		model.addAttribute("filename",filename);
        model.addAttribute("filepath",filepath);
			
		return "modifypage";
	}
	
	@RequestMapping("/modifysure")
	public String Modify(HttpSession session,HttpServletRequest request, 
			@RequestParam("newname") String filename,
			@RequestParam("filepath") String filepath,Model model) {
		Modifyfilename modifyfilename = new Modifyfilename();
		try {
			modifyfilename.rename(filepath, filename) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "redirect:http://localhost:8080/OnlineDisk/";
	}
}
