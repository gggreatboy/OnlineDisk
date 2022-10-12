package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import pojo.MultiFileDomain;

@Controller
public class MultiFilescontroller {
	 private static final Log logger = LogFactory.getLog(MultiFilescontroller.class);
	
	@RequestMapping("/upload")  //上传请求直接转到uploadmultiFiles.html
	public String toUploadMultiFile() {
		return "uploadmultiFiles";
	}
	
	
	@RequestMapping("/multifileupload")
	public String multiFileUpload(@ModelAttribute MultiFileDomain multiFileDomain,
													HttpSession session,HttpServletRequest request,Model model) {

		String realpath =request.getServletContext().getRealPath("/wangpan/");; //文件放到哪个目录下
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
		session.setAttribute("filelist1",fileList);
		return "showMulti";
	}

}
