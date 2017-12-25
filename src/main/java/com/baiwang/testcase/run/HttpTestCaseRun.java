package com.baiwang.testcase.run;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.baiwang.javabean.TestCase;
import com.baiwang.utils.Parse;

public class HttpTestCaseRun extends TestCaseRun {
	private String url;
	private String header;
	private String baowen;
	private String type;
	private String result;
	private static Logger logger = Logger.getLogger(HttpTestCaseRun.class.getName());
	@Override
	public boolean run(TestCase tc) throws Exception{
	    type = tc.getRequestType();
	    url = tc.getRequestUrl();
		header = tc.getRequestHeader();
		baowen = tc.getRequestBody();
		//logger.info(baowen);
		if(type.equals("POST")){
			postRun(tc);
		}else if(type.equals("GET")){
			getRun(tc);
		}else{
			logger.info("HTTP Request TYPE ERROR");
		}
		tc.setTestResponse(result);
		boolean testResult = check(tc);
		tc.setTestResult(String.valueOf(testResult));
		afterTest(tc);
		logger.info(result);
		return testResult;
		
	}
	private void postRun(TestCase tc) throws Exception{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		if(!header.equals("")){
			String[] headNum = header.split("\n");
			for(String head:headNum) {
				String[] arry = head.split("=");
				request.setHeader(arry[0],arry[1]);
			}
		}   
		StringEntity entry = new StringEntity(baowen,"gbk");
		request.setEntity(entry);
		HttpResponse response = client.execute(request);
		result = EntityUtils.toString(response.getEntity());
		client.close();
		
	}
	private void getRun(TestCase tc) throws Exception{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		if(!header.equals("")){
			String[] headNum = header.split("\n");
			for(String head:headNum) {
				String[] arry = head.split("=");
				request.setHeader(arry[0],arry[1]);
			}
		}
		HttpResponse response = client.execute(request);
		result = EntityUtils.toString(response.getEntity());
		client.close();
		
	}
	private boolean check(TestCase tc){
		String checkPoint = tc.getCheakPoint();
		logger.info(checkPoint);
		if(result.contains(checkPoint)){
			return true;
		}else{
			return false;
		}
	}
	private void afterTest(TestCase tc) throws Exception{
		Map<String,String> outParam = tc.getOutParam();
		if(!(outParam==null)){
			for(Map.Entry<String,String> entry:outParam.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(value.startsWith("$")){
					logger.info(value);
					String re = value.substring(value.indexOf("{")+1,value.indexOf("}"));
					Pattern p = Pattern.compile(re);
					Matcher m = p.matcher(result);
					if(m.find()){
						String val = m.group(1);
						value = value.replace(re,key+","+val);
					}
					
					value = Parse.parse(value);
					
				}
			}
		}
	}
}
