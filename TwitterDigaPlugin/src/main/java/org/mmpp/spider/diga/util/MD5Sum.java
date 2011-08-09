package org.mmpp.spider.diga.util;


public interface MD5Sum {

	public String b64digest(String nonce, String passwd);

	public java.util.List<String> strToArr(String string);


}
