package org.mmpp.spider.diga.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class JsMD5 {

	public List<String> doDigest(List<String> srcarr) {

		byte[] messages = JsUtil.listToBytes(srcarr);
	    try {
			MessageDigest md = null;
			md = MessageDigest.getInstance("MD5");
//			md.update(messages);
			return bytesToList( md.digest(messages));

	    } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
	}
	
	
	private List<String> bytesToList(byte[] bytes){
		List<String> results = new LinkedList<String>();
		for(byte b : bytes){
			int i = b;
			if(i < 0)
				i += 256;
			results.add(String.valueOf(i));
		}
		return results;
	}

}
