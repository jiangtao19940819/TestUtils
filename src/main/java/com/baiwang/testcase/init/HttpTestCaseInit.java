package com.baiwang.testcase.init;
import java.util.Map;
import com.baiwang.javabean.TestCase;
import com.baiwang.utils.Parse;
public class HttpTestCaseInit extends TestCaseInit{
	@Override
	public void init(TestCase tc) throws Exception {
		Map<String,String> reqParam = tc.getRequestParam();
		if(reqParam!=null){
			for(Map.Entry<String, String> entry:reqParam.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(value.startsWith("$")){
					value = Parse.parse(value);
					reqParam.put(key,value);
				}
			}
			tc.setRequestParam(reqParam);
			replaceParam(tc);
		}
	}
	private void replaceParam(TestCase tc){
		Map<String,String> reqParam = tc.getRequestParam();
		String headParam = tc.getRequestHeader();
		String baowen = tc.getRequestBody();
		for(String key:reqParam.keySet()){
			headParam = headParam.replace("${"+key+"}",reqParam.get(key));
			baowen = baowen.replace("${"+key+"}",reqParam.get(key));
			
		}
		tc.setRequestHeader(headParam);
		tc.setRequestBody(baowen);
	}
}
