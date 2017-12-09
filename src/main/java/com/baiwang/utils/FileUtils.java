package com.baiwang.utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FileUtils {
	public static void copyFile(String inFile,String outFileDir){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date now = new Date();
		File file = new File(inFile);
		String fileName = file.getName();
		fileName = fileName.substring(0,fileName.indexOf("."));
		String date = df.format(now);
		String resultFileName = fileName+date+"xlsx";
	}
}
