package org.mmpp.twitter.goo;

import java.io.IOException;
import java.util.List;

import org.mmpp.spider.goo.util.Html2AnimeUtil;
import org.mmpp.spider.goo.util.TelevisionProgram;
import org.mmpp.spider.util.CrawlerUtil;


public abstract class AbstractAnimeProgramParser implements AnimeProgramParser{

	/**
	 * 指定URLのアニメ情報を抽出します
	 * @param url 指定URL
	 * @return アニメ情報一覧
	 * @throws IOException 
	 */
	@Override
	public List<TelevisionProgram> parse() throws IOException {

		java.net.URLConnection conn = getUrl().openConnection();
		{
			conn.setRequestProperty("Cookie", "NGUserID=d2a0c960-335-1251462166-1; BTA=R003M000; gooproperty=AR%3D27142%26TH%3D0%26WE%3D6200%26MP%3Dkinki%26TR%3Dkinki%26TP%3D27%26TV%3D025; iepg=1; gcode=1; cast=1; searchstate=NAV%3D1");
		}
		// urlにアクセスします...
		String source = CrawlerUtil.webPageSource(conn,"euc-jp");
		// 内容を解析します....
		return Html2AnimeUtil.parse(source);
	}

	public abstract java.net.URL getUrl();

}
