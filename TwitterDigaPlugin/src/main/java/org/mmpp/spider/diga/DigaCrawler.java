package org.mmpp.spider.diga;

/**
 * DIGA Webアクセスクローラ 
 * @author kou
 *
 */
public interface DigaCrawler {

	public void setHostName(String hostname) ;

	public void login(String password);

	public void logout();
	
	public String status();

}
