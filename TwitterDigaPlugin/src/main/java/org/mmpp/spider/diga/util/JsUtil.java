package org.mmpp.spider.diga.util;

import java.util.LinkedList;
import java.util.List;

//import sun.misc.BASE64Encoder;  

public class JsUtil {

	public static List<String> strToArr(String string) {
		List<String> results = new LinkedList<String>();
		for(char c : string.toCharArray()){
			results.add(String.valueOf((int)(c)));
		}
		return results;
	}

	public static String arrToB64(List<String> digest) {
		byte[] bytes = listToBytes(digest);

		byte[] encodeValue = org.apache.commons.codec.binary.Base64.encodeBase64(bytes);

		return new String(encodeValue);
//		return (new BASE64Encoder()).encodeBuffer(bytes);
	}

	public static byte[] listToBytes(List<String> srcarr){
		byte[] results = new byte[ srcarr.size()];

		int i =0;
		for(String s : srcarr){
			byte b = ( (byte)(int)Integer.valueOf(s));
			results[i]=b;
			i++;
		}
		
		return results;
	}

}
