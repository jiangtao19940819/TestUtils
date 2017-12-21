package com.baiwang.function;

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
}
