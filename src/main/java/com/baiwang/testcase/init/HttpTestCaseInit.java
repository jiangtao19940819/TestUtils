package com.baiwang.testcase.init;
import java.util.Map;
import java.util.logging.Logger;

import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.testcase.run.HttpTestCaseRun;
import com.baiwang.utils.Parse;
public class HttpTestCaseInit extends TestCaseInit{
	private static Logger logger = Logger.getLogger(HttpTestCaseInit.class.getName());
	@Override
	public void init(TestCase tc) {
		Map<String,String> reqParam = tc.getRequestParam();
		if(reqParam!=null){
			for(Map.Entry<String, String> entry:reqParam.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(value.startsWith("$")){
					try{
						value = Parse.parse(value);
					}catch(Exception e){
						e.printStackTrace();
					}
					reqParam.put(key,value);
				}
			}
			tc.setRequestParam(reqParam);
			replaceParam(tc);
		}
	}
	private void replaceParam(TestCase tc){
		Map<String,String> reqParam = tc.getRequestParam();
		Map<String,String> repParam = tc.getOutParam();
		String headParam = tc.getRequestHeader();
		String baowen = tc.getRequestBody();
		String url = tc.getRequestUrl();
		String checkPoint = tc.getCheakPoint();
		for(String key:reqParam.keySet()){
			if(reqParam.get(key)!=null){
				headParam = headParam.replace("${"+key+"}",reqParam.get(key));
				baowen = baowen.replace("${"+key+"}",reqParam.get(key));
				url = url.replace("${"+key+"}",reqParam.get(key));
				checkPoint = checkPoint.replace("${"+key+"}",reqParam.get(key));
				if(repParam!=null){
					for(String k:repParam.keySet()){
						if(("${"+key+"}").equals(repParam.get(k))){
							repParam.put(k,reqParam.get(key));
						}
					}
				}
			}else{
				logger.info("从全局变量中获取："+key+" 失败");
			}
		}
		tc.setRequestHeader(headParam);
		tc.setRequestBody(baowen);
		tc.setRequestUrl(url);
		tc.setCheakPoint(checkPoint);
	}
}
