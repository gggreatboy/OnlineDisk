package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ioFile {



	public
	static void copyFolder(File srcFile, File destFile) throws IOException {
        //四、判断数据源File是否是目录，如果是，则：
        if(srcFile.isDirectory()){
            //在目的地下创建和数据源File名称一样的目录；
            String srcFileName = srcFile.getName(); //返回由此抽象路径名表示的文件或目录的名称
            File newFolder = new File(destFile,srcFileName);
            if(!newFolder.exists()){
                newFolder.mkdir(); //如果目录不存在，就创建由此对象路径名命名的目录，并返回true。
            }//目录已经存在时，就不创建目录，并返回false。
 
            //获取数据源File下所有文件或者目录的File数组；
            File[] fileArray = srcFile.listFiles(); //返回此抽象路径名表示的目录中的文件和目录的File对象数组
            //遍历该File数组，得到每一个File对象；
            for(File file:fileArray){
                //把该File作为数据源File对象，递归调用复制文件夹的方法。
                copyFolder(file,newFolder);
            }
        }else{
            //不是，则说明是文件，直接复制，用字节流。
            File newFile = new File(destFile,srcFile.getName());
            copyFile(srcFile,newFile);
        }
    }
    
    private static void copyFile(File srcFile, File destFile) throws IOException {
        //创建字节缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        //创建字节缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
 
        //一次读取和写入一个字节数组的数据
        byte[] bys = new byte[1024];
        int len;
        while((len=bis.read(bys)) != -1){
            bos.write(bys,0,len);
        }
 
        //释放资源
        bos.close();
        bis.close();
    }

}
