package com.common.util;

public class StringUtil {

	public static boolean isNullorEmpty(Object str) {
		
		boolean rtnFlag = true;
		
		if(str != null) {
			if(!"".equals(str.toString().trim())){
				rtnFlag = false;
			}
		}
		
		
		return rtnFlag;
	}
	
}
