package com.baiwang.MyTestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import com.baiwang.javabean.TestCase;
import com.baiwang.testcase.LoaderTestCase;
import com.baiwang.testcase.init.HttpTestCaseInit;
import com.baiwang.testcase.init.TestCaseInit;
import com.baiwang.testcase.run.HttpTestCaseRun;
import com.baiwang.testcase.run.TestCaseRun;
import com.baiwang.utils.ExcelUtils;
import com.baiwang.utils.FileUtils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
    		TestCaseRun tcr = new HttpTestCaseRun();
    		TestCase tc = caseList.get(0);
    		tci.init(tc);
    		String result = tcr.run(tc);
    		System.out.println(result);
    		//String result = "<?xml version=\"1.0\" encoding=\"gbk\"?><rtnmsg><returncode>4000</returncode><returnmsg>成功</returnmsg><returndata><fpdm>150007899689</fpdm><fphm>11727676</fphm><kprq>20171224154338</kprq></returndata></rtnmsg>";
    		String fpdm = "";
    		Pattern p = Pattern.compile(fpdm);
    		Matcher m = p.matcher(result);
    		m.find();
    		System.out.println(m.group(1));
       }
    
}
