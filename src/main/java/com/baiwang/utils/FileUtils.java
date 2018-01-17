package com.baiwang.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
public class FileUtils {
	public static String fileInputDir;
	public static String fileOutputDir;
	public static String testFile;
	private static Logger logger = Logger.getLogger(FileUtils.class.getName());
	static {
		try{
			System.out.println(System.getProperty("user.dir"));
			System.out.println(new File(System.getProperty("user.dir")).getParent());
			String filePath = System.getProperty("user.dir");
			Properties prop = new Properties();
			System.out.println(filePath+File.separator+"properties.properties");
			InputStreamReader is = new InputStreamReader(new FileInputStream(filePath+File.separator+"properties.properties"),"UTF-8");
			prop.load(is);
			fileInputDir = prop.getProperty("InputFileDir");
			System.out.println(">>>"+fileInputDir);
			if(fileInputDir.equals("")){
				fileInputDir = filePath+File.separator+"data"+File.separator+"测试用例文件";
			}
			fileOutputDir = prop.getProperty("OutputFileDir");
			if(fileOutputDir.equals("")){
				fileOutputDir = filePath+File.separator+"data"+File.separator+"测试结果文件";
			}
			testFile = prop.getProperty("TestFile");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static File[] getTestExcel(){
		File[] testExcelFile = null;
		if(!testFile.equals("")){
			String[] stringFile = testFile.split(",");
			testExcelFile = new File[stringFile.length];
			for(int i=0;i<stringFile.length;i++){
				testExcelFile[i] = new File(fileInputDir+File.separator+stringFile[i]);
			}
		}else{
			File inputFile = new File(fileInputDir);
			if(inputFile.exists()&&inputFile.isDirectory()){
				testExcelFile = inputFile.listFiles();
			}else{
				logger.info("测试用例文件路径错误");
			}
		}
		return testExcelFile;
	}
	public static boolean copyFile(File file,String time) {
		boolean flag = false;
		try{
			String resultName = time+file.getName();
			String testResultPath = fileOutputDir+File.separator+resultName;
			InputStream is = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			OutputStream os = new FileOutputStream(testResultPath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int num = 0;
			while((num=bis.read())!=-1){
				bos.write(num);
			}
			bis.close();
			bos.close();
			
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
