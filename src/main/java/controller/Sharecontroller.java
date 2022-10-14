package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.ioFile;

@Controller
public class Sharecontroller {
	

	@SuppressWarnings("static-access")
	@RequestMapping("/sharetoplace")
	public String sharetoplace(HttpSession session,HttpServletRequest request, 
			@RequestParam("file") String file,Model model) {
		String sharepath =(String) request.getServletContext().getAttribute("sharepath");
		File share=new File(sharepath);
		File Truefile=new File(file);
		
		ioFile iofile=new ioFile();
		try {
			iofile.copyFolder(Truefile, share);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String kongjian=(String)session.getAttribute("username");
		String path = request.getServletContext().getRealPath("/"+kongjian+"/");	
		File targetDir = new File(path);
		File fileListorigin[] =targetDir.listFiles();
		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir);
        session.setAttribute("filepathnow",targetDir);
        model.addAttribute("username",kongjian);
        session.setAttribute("username", kongjian);
        session.setAttribute("pathorigin1", path);

		return "index";
	}
	
	
	
	
	@RequestMapping("/openshare")
	public String gotoshareplace(HttpSession session,HttpServletRequest request,Model model) {
		String kongjian=(String)session.getAttribute("username");
		String path =(String) request.getServletContext().getAttribute("sharepath");
		
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
		
		
		return "sharepage";
	}
}
