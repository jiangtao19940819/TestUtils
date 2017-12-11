package com.baiwang.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static Logger logger = Logger.getLogger(ExcelUtils.class.getName());
	public static void readExcel(File file){
		String fileName = file.getName();
		if(fileName.endsWith("xlsx")){

		}else if(fileName.endsWith("xls")){
			
		}else{
			logger.info("测试用例文件必须是Excel");
		}
	}
	public void readExcel2007(File file) throws Exception{
		InputStream is = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		
	}
}
