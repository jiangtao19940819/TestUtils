package com.baiwang.function;
import com.baiwang.javabean.Global;
public class Function {
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
		Global.param.put(args[0],args[1]);
		return "success";
	}
	public String get(String key){
		return Global.param.get(key);
	}
}
