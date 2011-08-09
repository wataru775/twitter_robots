package org.mmpp.spider.diga;

import java.net.MalformedURLException;

import org.junit.Test;
import org.mmpp.spider.diga.DigaCrawler;
import org.mmpp.spider.diga.DigaCrawlerImpl;

import static org.junit.Assert.*;

public class DigaCrawlerTest {

	private DigaCrawler _webCrawler = new DigaCrawlerImpl();
	@Test
	public void testStatus() throws MalformedURLException{
		_webCrawler.setHostName("192.168.1.111");
//		_webCrawler.url("http://192.168.1.111/cgi-bin/topMenu.cgi");
		// ログインします。
		_webCrawler.login("kou307");
		String status = _webCrawler.status();
		
		_webCrawler.logout();
		assertNotNull(status);

		System.out.print(status);
	}
	// TODO 録画一覧を取得します。

}
