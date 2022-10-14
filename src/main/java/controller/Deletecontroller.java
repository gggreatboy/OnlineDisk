package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.Clear;

@Controller
public class Deletecontroller {
	@SuppressWarnings("static-access")
	@RequestMapping("/delete2")
	public String todelete(HttpSession session,HttpServletRequest request, 
			@RequestParam("file") File file,Model model) {
            Clear clear=new Clear();
		    clear.clearDir(file); 
			String kongjian=(String) session.getAttribute("username");
			String path1 = request.getServletContext().getRealPath("/"+kongjian+"/");
			
			File targetDir = new File(path1);
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
	        session.setAttribute("pathorigin1", path1);
		    
		    return "index";
	}
}
