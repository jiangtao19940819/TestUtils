package com.baiwang.utils;
import java.lang.reflect.Method;
import com.baiwang.function.Function;
public class Parse {
	public static String parse(String value) throws Exception{
		String funcName = value.substring(1,value.indexOf("("));
		String param = value.substring(value.indexOf("(")+1,value.indexOf(")"));
		Class<?> c = Function.class;
		Method m = c.getMethod(funcName,String.class);
		Object instance = c.newInstance();
		String result = (String)m.invoke(instance,param);
		return result;
	}
}
