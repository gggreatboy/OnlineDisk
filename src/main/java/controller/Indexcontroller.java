package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Indexcontroller {
	@RequestMapping({"/backtoindex", "/"})  //根请求直接转到index.html
	public String toUploadMultiFile(HttpSession session,HttpServletRequest request,Model model) {
		String path = request.getServletContext().getRealPath("/wangpan/");
		
		File targetDir = new File(path);
		File fileListorigin[] =targetDir.listFiles();

		model.addAttribute("filelistindex1",fileListorigin);
		model.addAttribute("filepath",targetDir);
		return "index";
	}
}
