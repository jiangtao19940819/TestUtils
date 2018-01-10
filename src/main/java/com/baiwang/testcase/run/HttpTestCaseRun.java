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

import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.utils.Parse;

public class HttpTestCaseRun extends TestCaseRun {
	private String url;
	private String header;
	private String baowen;
	private String type;
	private String result;
	private String response;
	private String checkPoint;
	Map<String,String> outParam;
	private static Logger logger = Logger.getLogger(HttpTestCaseRun.class.getName());
	@Override
	public void run(TestCase tc){
	    type = tc.getRequestType();
	    url = tc.getRequestUrl();
	    header = tc.getRequestHeader();
	    baowen = tc.getRequestBody();
	    checkPoint = tc.getCheakPoint();
	    outParam = tc.getOutParam();
	    try{
	    	if(type.equals("POST")){
		    	postRun(tc);
		    }else if(type.equals("GET")){
		    	getRun(tc);
		    }else{
		    	logger.info("HTTP Request TYPE ERROR");
		    	return;
		    }
	    	tc.setTestResponse(response);
	    	if(response.contains(checkPoint)){
	    		tc.setTestResult("Pass");
	    	}else{
	    		tc.setTestResult("Failure");
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	tc.setTestResponse("HTTP接口调用异常:"+e.toString());
	    	tc.setTestResult("Exception");
	    }
	    afterTest();
		
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
		StringEntity entry = new StringEntity(baowen,"utf-8");
		request.setEntity(entry);
		HttpResponse resp = client.execute(request);
		response = EntityUtils.toString(resp.getEntity());
		//logger.info("请求报文："+baowen);
		//logger.info("返回报文："+response);
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
		HttpResponse resp = client.execute(request);
		response = EntityUtils.toString(resp.getEntity());
		client.close();
		
	}
	
	private void afterTest(){
		try{
			if(!(outParam==null)){
				for(Map.Entry<String,String> entry:outParam.entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					if(value.startsWith("$")){
						String re = value.substring(value.indexOf("{")+1,value.indexOf("}"));
						Pattern p = Pattern.compile(re);
						Matcher m = p.matcher(response);
						if(m.find()){
							String val = m.group(1);
							value = value.replace(re,key+","+val);
						}
						value = Parse.parse(value);
					}else{
						Global.param.put(key, value);
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
