package com.common.util;

public class StringUtil {

	public static boolean isNullorEmpty(String str) {
		
		boolean rtnFlag = true;
		
		if(str != null) {
			if(!"".equals(str.trim())){
				rtnFlag = false;
			}
		}
		
		
		return rtnFlag;
	}
	
}
