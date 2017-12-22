package com.baiwang.testcase.run;

import java.util.logging.Logger;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.baiwang.javabean.TestCase;

public class HttpTestCaseRun extends TestCaseRun {
	private String url;
	private String header;
	private String baowen;
	private static Logger logger = Logger.getLogger(HttpTestCaseRun.class.getName());
	@Override
	public void run(TestCase tc) throws Exception{
		String type = tc.getRequestType();
		if(type.equals("POST")){
			
		}else if(type.equals("GET")){
			
		}else{
			logger.info("HTTP Request TYPE ERROR");
		}
	}
	private void postRun(TestCase tc){
		url = tc.getRequestUrl();
		header = tc.getRequestHeader();
		baowen = tc.getRequestBody();
		CloseableHttpClient client = HttpClients.createDefault();
		if(!header.equals("")){
			String[] array = header.split("=");
		}
	}
}
