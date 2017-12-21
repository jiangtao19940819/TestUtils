package com.baiwang.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static Logger logger = Logger.getLogger(ExcelUtils.class.getName());
	public static List<List<String>> readExcel(File file) throws Exception{
		List<List<String>> rowList = new ArrayList<List<String>>();
		String fileName = file.getName();
		if(fileName.endsWith("xlsx")){
			rowList = readExcel2007(file);
		}else if(fileName.endsWith("xls")){
			rowList = readExcel2003(file);
		}else{
			logger.info("测试用例文件必须是Excel");
		}
		return rowList;
	}
	private static List<List<String>> readExcel2007(File file) throws Exception{
		List<List<String>> rowList = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);
		int cellNum = firstRow.getPhysicalNumberOfCells();
		int rowNum = sheet.getLastRowNum();
		for(int i=1;i<=rowNum;i++) {
			XSSFRow row = sheet.getRow(i);
			List<String> colList = new ArrayList<String>();
			for(int a=0;a<cellNum;a++) {
				String content = "";
				XSSFCell  cell = row.getCell(a);
				
				if(cell!=null) {
					content = cell.toString();
				}
			    colList.add(content);
			}
			rowList.add(colList);
			
		}
		wb.close();
		return rowList;
	}
	private static List<List<String>> readExcel2003(File file) throws Exception{
		List<List<String>> rowList = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow firstRow = sheet.getRow(0);
		int cellNum = firstRow.getPhysicalNumberOfCells();
		int rowNum = sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rowNum;i++){
			HSSFRow row = sheet.getRow(i);
			List<String> colList = new ArrayList<String>();
			for(int a=0;a<cellNum;a++){
				String content = "";
				HSSFCell cell = row.getCell(a);
				if(cell!=null){
					content = cell.toString();
				}
				colList.add(content);
			}
			rowList.add(colList);
		}
		return rowList;
	}
}
