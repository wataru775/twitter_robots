package org.mmpp.spider.diga.util;

import org.junit.Test;
import org.mmpp.spider.diga.util.JsMD5;

import static org.junit.Assert.*;


public class JsMD5Test {

	private JsMD5 md5 = new JsMD5();
	
	@Test
	public void testDoDigest(){
		java.util.List<String> srcarr = java.util.Arrays.asList(new String[]{"49","51","51","49","51","48","48","50","57","51","107","111","117","51","48","55"});
		java.util.List<String> results = java.util.Arrays.asList(new String[]{"8","23","67","113","227","192","128","42","159","183","150","167","125","77","119","214"});

		assertEquals(results , md5.doDigest(srcarr));
	}
}
