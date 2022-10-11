package controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Downloadcontroller {
	@RequestMapping("/download")  //下载请求直接转到download.html
	public String toDownload() {
		return "download";
	}
	
	@RequestMapping("/downloadfile")
	public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename)throws Exception {
	   //下载文件路径
	   String path = request.getServletContext().getRealPath("/wangpan/");
	   File file = new File(path + File.separator + filename);
	   HttpHeaders headers = new HttpHeaders();  
	   //下载显示的文件名，解决中文名称乱码问题
	   String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
	   //通知浏览器以attachment（下载方式）打开图片
	   headers.setContentDispositionFormData("attachment", downloadFielName); 
	   //application/octet-stream ： 二进制流数据（最常见的文件下载）。
	   headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	   return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);  
	}
}
