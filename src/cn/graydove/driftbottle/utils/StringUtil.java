package cn.graydove.driftbottle.utils;

public class StringUtil {
	
	private StringUtil(){
		
	}
	
	public static boolean empty(String str) {
		if(str==null) {
			return true;
		}
		if("".equals(str)) {
			return true;
		}
		return false;
	}
}
