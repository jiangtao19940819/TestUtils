package com.baiwang.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

import com.baiwang.javabean.TestCase;

import java.util.Properties;
public class ExcelUtils {
	
	private static Logger logger = Logger.getLogger(ExcelUtils.class.getName());
	private static String outDataDir;
	static{
		try{
			Properties prop = new Properties();
			InputStreamReader is = new InputStreamReader(new FileInputStream("properties.properties"),"UTF-8");
			prop.load(is);
			outDataDir = prop.getProperty("OutputFileDir");
			logger.info(">>>>>"+outDataDir);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
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
	/**
	 * @param inputFile 测试用例文件
	 * @param caseList  需要填写结果的测试用例集
	 * @param time      时间戳，通过测试用例文件和时间戳得到测试结果文件
	 * @return
	 */
	public static boolean writeExcel(File inputFile,List<TestCase> caseList,String time){
		boolean flag = false;
		try{
			String fileName = inputFile.getName();
			if(fileName.endsWith(".xlsx")){
				flag = write2007(fileName,time,caseList);
			}
			if(fileName.endsWith(".xls")){
				flag = write2003(fileName,time,caseList);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("》》》测试结果填写异常"+e.toString());
		}
		return flag;
	}
	
	/**
	 * @param fileName
	 * @param time   通过时间戳和测试用例文件fileName得到测试结果文件
	 * @param caseList
	 * @return
	 * @throws Exception
	 */
	private static boolean write2007(String fileName,String time,List<TestCase> caseList) throws Exception{
		String filePath = outDataDir+File.separator+time+fileName;
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
		XSSFSheet sheet = wb.getSheetAt(0);
		for(int i=0;i<caseList.size();i++){
			XSSFRow row = sheet.getRow(i+2);
			XSSFCell outBaowen = row.createCell(11);
			XSSFCell outResult = row.createCell(12);
			outBaowen.setCellValue(caseList.get(i).getTestResponse());
			outResult.setCellValue(caseList.get(i).getTestResult());
		}
		FileOutputStream os = new FileOutputStream(filePath);
		wb.write(os);
		wb.close();
		os.close();
		return true;
	}
	private static boolean write2003(String fileName,String time,List<TestCase> caseList) throws Exception{
		String filePath = outDataDir+File.separator+time+fileName;
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = wb.getSheetAt(0);
		for(int i=0;i<caseList.size();i++){
			HSSFRow row = sheet.getRow(i+2);
			HSSFCell outBaowen = row.getCell(11);
			outBaowen.setCellValue(caseList.get(i).getTestResponse());
			HSSFCell outResult = row.getCell(12);
			outResult.setCellValue(caseList.get(i).getTestResult());
		}
		OutputStream os = new FileOutputStream(filePath);
		wb.write(os);
		wb.close();
		os.close();
		return true;
	}
	
	private static List<List<String>> readExcel2007(File file) throws Exception{
		List<List<String>> rowList = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(1);
		int cellNum = firstRow.getPhysicalNumberOfCells();
		int rowNum = sheet.getLastRowNum();
		for(int i=0;i<=rowNum;i++) {
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
		HSSFRow firstRow = sheet.getRow(1);
		int cellNum = firstRow.getPhysicalNumberOfCells();
		int rowNum = sheet.getPhysicalNumberOfRows();
		for(int i=0;i<rowNum;i++){
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
		wb.close();
		return rowList;
	}
}
