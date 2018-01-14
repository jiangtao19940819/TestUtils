package com.baiwang.MyTestUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.testcase.LoaderTestCase;
import com.baiwang.testcase.init.HttpTestCaseInit;
import com.baiwang.testcase.init.TestCaseInit;
import com.baiwang.testcase.run.HttpTestCaseRun;
//import com.baiwang.testcase.run.SDKTestCaseRun;
import com.baiwang.testcase.run.TestCaseRun;
import com.baiwang.utils.ExcelUtils;
import com.baiwang.utils.FileUtils;
import com.baiwang.utils.Mail;

public class Client {
	public static void run() throws Exception{
		String reMessage = "";
		int success = 0;
 		int fail = 0;
 		int skip = 0;
 		int exception = 0;
 		int num = 0;
 		File[] fileArray = FileUtils.getTestExcel();
 		String[] sf = new String[fileArray.length+1];
 		TestCaseInit ti = null;
		TestCaseRun tr = null;
 		for(int i=0;i<fileArray.length;i++){
 			String fileName = fileArray[i].getName();
 			String fMessage = "";
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
 			String time = sdf.format(new Date());
 			FileUtils.copyFile(fileArray[i], time);
 			List<TestCase> caseList = LoaderTestCase.loader(fileArray[i]);
 			int error = 0;
 			String s = "";
 			for(TestCase tc:caseList){
 				if(!tc.isCaseStatus()){
 					tc.setTestResponse("此用例跳过");
 					tc.setTestResult("Skip");
 					num++;
 					skip++;
 					continue;
 				}
 				if(tc.getRequestType().equals("POST")||tc.getRequestType().equals("GET")){
 					 ti = new HttpTestCaseInit();
 					 tr = new HttpTestCaseRun();
 					
 				}
 				/*if(tc.getRequestType().equals("SDK")){
 					 ti = new HttpTestCaseInit();
 					 tr = new SDKTestCaseRun();
 				}*/
 				ti.init(tc);
	    		tr.run(tc);
	    		num++;
                if(tc.getTestResult().equals("Pass")){
                 	success++;
                 }
                if(tc.getTestResult().equals("Failure")){
                 	fail++;
                 	fMessage+="*"+tc.getApiName()+"*"+tc.getCaseName()+"*调用失败;";
                 	error++;
                 }
                if(tc.getTestResult().equals("Skip")){
                 	skip++;
                 	fMessage+="*"+tc.getApiName()+"*"+tc.getCaseName()+"*调用异常;";
                  }
                if(tc.getTestResult().equals("Exception")){
                 	exception++;
                 	error++;
                 }
 			}
 			if(error>0){
 				 s = "{测试用例集-"+ fileArray[i].getName().substring(0,fileName.indexOf("."))+"}测试有"+error+"错误:"+fMessage;
 			}else{
 				 s = "{测试用例集-"+ fileArray[i].getName().substring(0,fileName.indexOf("."))+"}测试全部成功。";
 			}
 			
 			if(ExcelUtils.writeExcel(fileArray[i], caseList, time)){
 				System.out.println("测试用例集"+fileArray[i].getName()+"运行成功");
 			}
 			reMessage+=s;
 			sf[i] = time+fileName;
 			
 		}
 		sf[fileArray.length] = "总共运行:"+num+"条测试用例"+" 成功:"+success+" 失败:"+fail+" 跳过:"+skip+" 异常:"+exception+"。 "+reMessage;
 		//Mail.sendMail(sf);
 		System.out.println("总共运行:"+num+"条测试用例"+" 成功:"+success+" 失败:"+fail+" 跳过:"+skip+" 异常:"+exception+"。 "+reMessage);
	}
}
