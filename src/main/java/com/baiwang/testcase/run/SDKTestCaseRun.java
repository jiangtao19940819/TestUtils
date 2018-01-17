package com.baiwang.testcase.run;

import com.baiwang.javabean.Global;
import com.baiwang.javabean.TestCase;
import com.baiwang.utils.Parse;

import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDKTestCaseRun{
	
}
/*import com.baiwang.baiwangcloud.*;
import com.baiwang.baiwangcloud.client.BaiwangCouldAPIClient;
public class SDKTestCaseRun extends TestCaseRun{
	private String url;
	private String baowen;
	private String nsrsbh;
	private String jrdm;
	private String kpzddm;
	private String checkPoint;
	private BaiwangCouldAPIClient client;
	private String response;
	private String result;
	private Map<String,String> outParam;
	private static Logger logger = Logger.getLogger(SDKTestCaseRun.class.getName());
	@Override
	public void run(TestCase tc){
		if(beforeTest(tc)){
			try{
				baowen = tc.getRequestBody();
				response = client.rpc(baowen);
				//logger.info("SDK请求报文： "+baowen);
				//logger.info("SDK请求返回: "+response);
				tc.setTestResponse(response);
				if(response.contains(checkPoint)){
					tc.setTestResult("Pass");
				}else{
					tc.setTestResult("Failure");
				}
				afterTest();
			}catch(Exception e){
				e.printStackTrace();
				tc.setTestResponse("百望云接口调用异常："+e.toString());
				tc.setTestResult("Exception");
			}
		}else{
			logger.info("百望云接口登录失败");
			tc.setTestResponse("百望云接口登陆失败"+response);
			tc.setTestResult("Exception");
		}
			
	}
	
	*//**
	 * @param 从全局变量和TestCase中获取请求需要的参数，并登录
	 * @return
	 *//*
	private boolean beforeTest(TestCase tc){
		boolean flag = false;
		try{
			url = tc.getRequestUrl();
			nsrsbh = Global.param.get("NSRSBH");
			jrdm = Global.param.get("JRDM");
			client = new BaiwangCouldAPIClient();
			checkPoint = tc.getCheakPoint();
			outParam = tc.getOutParam();
			client.login(url,nsrsbh, jrdm);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			response = e.toString();
		}
		return flag;
	}
	
	private void afterTest(){
		try{
			 client.logout();
			 if(outParam!=null){
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
*/