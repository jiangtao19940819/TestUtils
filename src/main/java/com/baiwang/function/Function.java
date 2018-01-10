package com.baiwang.function;
import java.util.logging.Logger;

import com.baiwang.javabean.Global;
public class Function {
	private static Logger logger = Logger.getLogger(Function.class.getName());
	public String getRandomNumber(String num){
		int num2 = Integer.valueOf(num);
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<num2;i++){
			int a = (int)(Math.random()*10);
			sb.append(a);
		}
		return sb.toString();
	}
	public String set(String value){
		String[] args = value.split(",");
		if(args.length==1){
			logger.info("将 "+args[0]+" 放入全局变量失败");
			return "failure";
		}
		Global.param.put(args[0],args[1]);
		return "success";
	}
	public String get(String key){
		return Global.param.get(key);
	}
}
