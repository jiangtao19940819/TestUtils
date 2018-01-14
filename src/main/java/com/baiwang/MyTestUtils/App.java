package com.baiwang.MyTestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.testcase.LoaderTestCase;
import com.baiwang.testcase.init.HttpTestCaseInit;
import com.baiwang.testcase.init.TestCaseInit;
import com.baiwang.testcase.run.HttpTestCaseRun;

import com.baiwang.testcase.run.TestCaseRun;
import com.baiwang.utils.ExcelUtils;
import com.baiwang.utils.FileUtils;
import com.baiwang.utils.Mail;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws Exception{
    	   try{
    		  Client.run(); 
    	   }catch(Exception e){
    		   e.printStackTrace();
    	   }
    		
       }
    
}
