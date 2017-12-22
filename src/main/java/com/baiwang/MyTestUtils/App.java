package com.baiwang.MyTestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import com.baiwang.javabean.TestCase;
import com.baiwang.testcase.LoaderTestCase;
import com.baiwang.testcase.init.HttpTestCaseInit;
import com.baiwang.testcase.init.TestCaseInit;
import com.baiwang.utils.ExcelUtils;
import com.baiwang.utils.FileUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception{
    		File file = new File("data/测试用例文件/阿里前置接口.xlsx");
    		List<List<String>> rowList = ExcelUtils.readExcel(file);
    		List<TestCase> caseList = LoaderTestCase.loader(rowList);
    		TestCaseInit tci = new HttpTestCaseInit();
    		for(TestCase tc:caseList){
    			tci.init(tc);
    			System.out.println(tc.getRequestBody());
    			System.out.println(tc.getRequestHeader());
    		}
       }
    
}
