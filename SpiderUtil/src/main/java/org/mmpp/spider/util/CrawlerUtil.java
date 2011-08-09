package org.mmpp.spider.util;

import java.io.IOException;
import java.net.URL;

public class CrawlerUtil {

	/**
	 * 指定URLのページソースを取得します
	 * @param url 指定URL
	 * @return ページソース
	 */
	public static String webPageSource(URL url,String encoding) {
		try {
			return webPageSource(url.openConnection(),encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Webページ内容を取得します
	 * @param urlConnection
	 * @return ページソース
	 */
	public static String webPageSource(java.net.URLConnection urlConnection,String encoding){
		StringBuffer bufPageSource = new StringBuffer();
		java.io.BufferedReader reader = null;
		try {
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(urlConnection.getInputStream(),encoding));
			String s;
			while ((s = reader.readLine()) != null) {
				bufPageSource.append(s);
				bufPageSource.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
					try {
						reader.close();
					} catch (IOException e) {
				}			
			}
		}
		return bufPageSource.toString();
	}

}
