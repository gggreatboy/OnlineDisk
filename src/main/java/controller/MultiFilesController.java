package cn.edu.zjweu.ie.ch2_6.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zjweu.ie.ch2_6.pojo.MultiFileDomain;

@Controller
public class MultiFilesController {
	@RequestMapping("/")  //根请求直接转到multifiles.html
	public String toMultiFile() {
		return "multifiles";
	}
	
	@RequestMapping("/multifile")
	public String multiFileUpload(@ModelAttribute MultiFileDomain multiFileDomain,
													HttpServletRequest request,Model model) {
		String realpath = request.getServletContext().getRealPath("uploadfiles");//文件放到哪个目录下
		
		File targetDir = new File(realpath);
		if(!targetDir.exists()) {
			targetDir.mkdirs();
		}
		
		List<MultipartFile> files = multiFileDomain.getMyfile();
		
		for(MultipartFile file:files) {
			String fileName = file.getOriginalFilename();
			File targetFile = new File(realpath,fileName);
			
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 保存文件
		}
		
		File fileList[] = targetDir.listFiles();
		model.addAttribute("filelist",fileList);
		
		return "showMulti";
	}

}
