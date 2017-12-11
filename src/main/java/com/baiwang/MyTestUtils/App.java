package com.baiwang.MyTestUtils;

import java.io.File;

import com.baiwang.utils.FileUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       File[] file = FileUtils.getTestExcel();
       for(File f:file){
    	   FileUtils.copyFile(f);
       }
       System.out.println("success");
    }
}
