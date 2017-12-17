package com.baiwang.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static Logger logger = Logger.getLogger(ExcelUtils.class.getName());
	public static File file;
	public static void main(String[] args) throws Exception {
		 file = new File("data/测试用例文件/阿里前置接口.xlsx");
		 List<List<String>> alist = ExcelUtils.readExcel2007(file);
		 List<String> blist = alist.get(0);
		 String s = blist.get(8);
		 String baowen = blist.get(7);
		 String[] array = s.split(System.getProperty("line.separator"));
		 //System.out.println(s);
		 Map<String,String> map = new HashMap<String,String> ();
		 for(String a:array) {
			 String[] arr = a.split("=");
			 map.put(arr[0],arr[1]);
		 }
		 for(Map.Entry<String,String> entry:map.entrySet()) {
			 String key = entry.getKey();
			 String value = entry.getValue();
			 String realKey = "${"+key+"}";
			 baowen = baowen.replace(realKey,value);
		 }
		 System.out.println(baowen);
	}
	public static void readExcel(File file) throws Exception{
		String fileName = file.getName();
		if(fileName.endsWith("xlsx")){
			readExcel2007(file);
		}else if(fileName.endsWith("xls")){
			
		}else{
			logger.info("测试用例文件必须是Excel");
		}
	}
	public static List<List<String>> readExcel2007(File file) throws Exception{
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
				//System.out.println(content);
			    colList.add(content);
			}
			rowList.add(colList);
			
		}
		//System.out.println(rowList);
		wb.close();
		return rowList;
	}
}
