package com.baiwang.MyTestUtils;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws Exception{
    	   try{
    		   System.out.println(System.getProperty("user.dir"));
    		  Client.run(); 
    	   }catch(Exception e){
    		   e.printStackTrace();
    	   }
    		
       }
    
}
