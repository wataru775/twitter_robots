package org.mmpp.spider.diga.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mmpp.spider.diga.util.MD5Sum;
import org.mmpp.spider.diga.util.MD5SumImpl;

public class MD5SumTest {

	MD5Sum _rdmd5Sum = new MD5SumImpl();
	@Test
	public void testStrToArr(){
		String nonce = "1331300293";
		String passwd = "kou307";
		java.util.List<String> results = java.util.Arrays.asList(new String[]{"49","51","51","49","51","48","48","50","57","51","107","111","117","51","48","55"});
		
		assertEquals(results, _rdmd5Sum.strToArr(nonce+passwd));
	}

	@Test
	public void testArrToB64(){
		String nonce = "1331300293";
		String passwd = "kou307";
		String result = "CBdDcePAgCqft5anfU131g==";
		String methodResult =  _rdmd5Sum.b64digest(nonce,passwd);
//		assertEquals(result.length(),methodResult.length());
		assertArrayEquals(result.getBytes(),methodResult.getBytes());
		assertArrayEquals(result.toCharArray(),methodResult.toCharArray());
		assertEquals(result,methodResult);
	}
}
