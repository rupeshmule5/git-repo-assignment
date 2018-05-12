package com.telstra.codechallenge.reversstring;

import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

@Service
public class ReverseStringService {

	public String reverseString(String str) {
		
		 StringBuilder result=new StringBuilder();  
	        StringTokenizer tokenizer=new StringTokenizer(str," ");  
	  
	        while(tokenizer.hasMoreTokens()){  
	        StringBuilder sb=new StringBuilder();  
	        sb.append(tokenizer.nextToken());  
	        sb.reverse();  
	  
	        result.append(sb);  
	        result.append(" ");  
	        }  
	        return result.toString();  
	}
}
