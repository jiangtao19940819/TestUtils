package com.baiwang.testcase;
import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.utils.ExcelUtils;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class LoaderTestCase {
	
	public static List<TestCase> loader(File file) throws Exception{
		List<List<String>> rowList = ExcelUtils.readExcel(file);
		List<TestCase> caseList = new ArrayList<TestCase>();
		//每次加载测试用例集时加载全局变量
		String global = rowList.get(0).get(1);
		if(!global.equals("")){
			String[] array = global.split("\n");
			for(String str:array){
				String[] strArry = str.split("=");
				Global.param.put(strArry[0],strArry[1]);
			}
		}
		
		//将excel转换成testcase
		for(List<String> cellList:rowList){
			if(!(cellList.get(0).equals("全局变量")||cellList.get(0).equals("编号"))){
				TestCase tc = new TestCase();
				tc.setCaseId(Integer.valueOf(cellList.get(0).substring(0,cellList.get(0).indexOf("."))));
				tc.setCaseStatus(cellList.get(1).equals("YES")?true:false);
				tc.setApiName(cellList.get(2));
				tc.setCaseName(cellList.get(3));
				tc.setRequestUrl(cellList.get(4));
				tc.setRequestType(cellList.get(5));
				tc.setRequestHeader(cellList.get(6));
				tc.setRequestBody(cellList.get(7));
				if(!cellList.get(8).equals("")){
					String value = cellList.get(8);
					if(Global.param.size()!=0){
						for(Map.Entry<String,String> entry: Global.param.entrySet()){
							String key = entry.getKey();
							String val= entry.getValue();
							value = value.replace("${"+key+"}",val);
							
						}
						cellList.set(8,value);
					}
					tc.setRequestParam(getMap(cellList.get(8)));
				}
				tc.setCheakPoint(cellList.get(9));
				if(!cellList.get(10).equals("")){
					tc.setOutParam(getMap(cellList.get(10)));
				}
				tc.setTestResult(cellList.get(11));
				tc.setRunTime(cellList.get(12));
				caseList.add(tc);
			}
		}
		return caseList;
	}
	
	public static Map<String,String> getMap(String value){
		Map<String,String> map = new HashMap<String,String>();
		
		String[] arr = value.split("\n");
		for(String str:arr){
			String[] strArry = str.split("=");
			map.put(strArry[0],strArry[1]);
		}
		return map;
	}
	
}
