package com.baiwang.MyTestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import com.baiwang.utils.ExcelUtils;
import com.baiwang.utils.FileUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception{
    		File file = new File("data/测试用例文件/test.xls");
    		List<List<String>> alist = ExcelUtils.readExcel(file);
    		System.out.println(alist);
       }
    
}
