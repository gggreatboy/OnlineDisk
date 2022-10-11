package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import pojo.MultiFileDomain;

@Controller
public class MultiFilesController {
	 private static final Log logger = LogFactory.getLog(MultiFilesController.class);
	
	@RequestMapping("/")  //根请求直接转到multifiles.html
	public String toMultiFile() {
		return "multiFiles";
	}
	
	@RequestMapping("/multifile")
	public String multiFileUpload(@ModelAttribute MultiFileDomain multiFileDomain,
													HttpServletRequest request,Model model) {
		String realpath ="E://wangpantest/upload";//文件放到哪个目录下
		
		File targetDir = new File(realpath);
		if(!targetDir.exists()) {
			targetDir.mkdirs();
		}
		
		List<MultipartFile> files = multiFileDomain.getMyfile();
		
		for(MultipartFile file:files) {
			if(file.isEmpty()) {
				continue;
			}
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
			finally{
				targetFile=null;
			}
			
		}
		
		File fileList[] = targetDir.listFiles();
		model.addAttribute("filelist",fileList);
		
		return "showMulti";
	}

}
