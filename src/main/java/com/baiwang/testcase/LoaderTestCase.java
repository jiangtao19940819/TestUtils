package com.baiwang.testcase;
import com.baiwang.javabean.TestCase;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class LoaderTestCase {
	public List<TestCase> loader(List<List<String>> rowList){
		List<TestCase> caseList = new ArrayList<TestCase>();
		for(List<String> cellList:rowList){
			TestCase tc = new TestCase();
			tc.setCaseId(Integer.valueOf(cellList.get(0)));
			tc.setCaseStatus(cellList.get(1).equals("YES")?true:false);
			tc.setApiName(cellList.get(2));
			tc.setCaseName(cellList.get(3));
			tc.setRequestUrl(cellList.get(4));
			tc.setRequestType(cellList.get(5));
			tc.setRequestHeader(getMap(cellList.get(6)));
			tc.setRequestBody(cellList.get(7));
			tc.setRequestParam(getMap(cellList.get(8)));
			tc.setCheakPoint(cellList.get(9));
			tc.setOutParam(getMap(cellList.get(10)));
			tc.setTestResult(cellList.get(11));
			tc.setRunTime(cellList.get(12));
			caseList.add(tc);
		}
		return caseList;
	}
	
	public Map<String,String> getMap(String value){
		Map<String,String> map = new HashMap<String,String>();
		String[] arr = value.split("\n");
		for(String str:arr){
			String[] strArry = str.split("=");
			map.put(strArry[0],strArry[1]);
		}
		return map;
	}
	
}
