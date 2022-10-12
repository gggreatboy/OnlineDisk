package service;

import java.io.File;
import java.io.IOException;

public class Modifyfilename {
	 public String rename(String path/*原文件路径+文件名*/,String newname/*要修改成的文件名*/) throws IOException {
	        File oldFile = new File(path);
	        if(!oldFile.exists())
	        {
	            return" no";//如果原文件不存在,返回no
	        }
	        System.out.println("原文件名称："+oldFile.getName());
	        String rootPath = oldFile.getParent();
	        System.out.println("根路径是："+rootPath);
	        File newFile = new File(rootPath + File.separator + newname);
	        System.out.println("修改后文件名称是："+newFile.getName());
	        if (oldFile.renameTo(newFile))
	        {
	            System.out.println("修改成功!");
	            return "ok";
	        }
	        else
	        {
	            System.out.println("修改失败");
	            return" no";
	        }
	    }

}
