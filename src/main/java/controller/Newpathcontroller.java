package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Newpathcontroller {
	@RequestMapping("/newpath")  
	public String tonewpath(@RequestParam("newpath") String newpath,
			HttpSession session,HttpServletRequest request,Model model) throws IOException {
		String nowpath;
		String kongjian=(String) session.getAttribute("username");
		File path = (File) session.getAttribute("filepathnow");
		if(path==null) {
			 nowpath=request.getServletContext().getRealPath("/"+kongjian+"/");
		}else {
		nowpath= path.getCanonicalPath();}
		String newpath1=nowpath+"/"+newpath;
		File targetDir = new File(newpath1);
		if(!targetDir.exists()) {
			targetDir.mkdirs();}


		
		
		String kongjian2=(String)session.getAttribute("username");
		String path2 = request.getServletContext().getRealPath("/"+kongjian+"/");
		
		File targetDir2 = new File(path2);
		File fileListorigin[] =targetDir2.listFiles();
   //     List <String> list=(List<String>) session.getAttribute("filemult");
   //     list.get(0)
		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir2);
        session.setAttribute("filepathnow",targetDir2);
        model.addAttribute("username",kongjian2);
        session.setAttribute("username", kongjian2);
        session.setAttribute("pathorigin1", path2);
		
		return "index";
	}
}

	
