package com.baiwang.utils;

public class Method {
	public String getRandomNum(int num){
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<num;i++) {
			int a = (int)(Math.random()*10);
			sb.append(String.valueOf(a));
		}
		return sb.toString();
	}
}
