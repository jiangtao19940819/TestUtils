package com.baiwang.utils;

import org.testng.annotations.Test;
import java.io.File;
public class ExcelUtilsTest {
	public static File file;
  @Test
  public static void readExcel2007() throws Exception{
	 file = new File("data/测试用例文件/阿里前置接口.xlsx");
    ExcelUtils.readExcel2007(file);
  }
}
