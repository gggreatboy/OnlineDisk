package controller;

import java.io.File;
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
			
		return "modifypage1";
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

		String kongjian=(String)session.getAttribute("username");
		String path = request.getServletContext().getRealPath("/"+kongjian+"/");
		
		File targetDir = new File(path);
		File fileListorigin[] =targetDir.listFiles();
   //     List <String> list=(List<String>) session.getAttribute("filemult");
   //     list.get(0)
		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir);
        session.setAttribute("filepathnow",targetDir);
        model.addAttribute("username",kongjian);
        session.setAttribute("username", kongjian);
        session.setAttribute("pathorigin1", path);	
		return "index";
	}
}
