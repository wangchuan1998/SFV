package com.sfv.common;

import java.util.Map;

public class VertityUtil {
	
	
	public static boolean isEmpty(String s){
		if(null == s || s.equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyMap(Map m){
		if(null == m || m.isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean isNum(String str){
		
		return true;
	}
}
