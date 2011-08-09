package org.mmpp.spider.diga.util;

import java.util.List;

public class MD5SumImpl implements MD5Sum {

	private JsMD5 md5 = new JsMD5();
	@Override
	public String b64digest(String nonce, String passwd) {
		String src=nonce + passwd;
		List<String> srcarr=JsUtil.strToArr(src);
		List<String> digest=md5.doDigest(srcarr);
		return JsUtil.arrToB64(digest);
	}
	@Override
	public List<String> strToArr(String string) {
		return JsUtil.strToArr(string);
	}

}
