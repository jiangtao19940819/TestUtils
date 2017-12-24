package com.baiwang.testcase.run;

import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.baiwang.javabean.TestCase;

public class HttpTestCaseRun extends TestCaseRun {
	private String url;
	private String header;
	private String baowen;
	private String type;
	private static Logger logger = Logger.getLogger(HttpTestCaseRun.class.getName());
	@Override
	public String run(TestCase tc) throws Exception{
	    type = tc.getRequestType();
	    url = tc.getRequestUrl();
		header = tc.getRequestHeader();
		baowen = tc.getRequestBody();
		String result = "";
		if(type.equals("POST")){
			result = postRun(tc);
		}else if(type.equals("GET")){
			result = getRun(tc);
		}else{
			logger.info("HTTP Request TYPE ERROR");
		}
		return result;
	}
	private String postRun(TestCase tc) throws Exception{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		if(!header.equals("")){
			String[] headNum = header.split("\n");
			for(String head:headNum) {
				String[] arry = head.split("=");
				request.setHeader(arry[0],arry[1]);
			}
		}
		StringEntity entry = new StringEntity(baowen);
		request.setEntity(entry);
		HttpResponse response = client.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		client.close();
		return result;
	}
	private String getRun(TestCase tc) throws Exception{
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
		String result = EntityUtils.toString(response.getEntity());
		client.close();
		return result;
	}
}
