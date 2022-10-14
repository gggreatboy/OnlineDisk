package service;

import java.io.File;

public class Clear {
	public static void clearDir(File file) {  
        if (file.isDirectory()) {  
            for (File f : file.listFiles()) {  
                clearDir(f);  
                f.delete();  
            }  
        }  
        file.delete();  
    }  
}
