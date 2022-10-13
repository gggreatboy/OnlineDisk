package controller;

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

@Controller
public class Deletecontroller {
	@RequestMapping("/delete2")
	public String todelete(HttpSession session,HttpServletRequest request, 
			@RequestParam("filepath") String filepath,Model model) {
		    Path path = Paths.get(filepath);
		    try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        return "redirect:http://localhost:8080/OnlineDisk/";
	}
}
