package org.mmpp.twitter.api;

import twitter4j.TwitterException;

/**
 * つぶやきサービスインタフェイス
 *
 */
public interface TweatService {
	/**
	 * ログインできること
	 * @throws TwitterException 
	 */
	public void login() throws TwitterException;
	/**
	 * ログアウトします
	 * @throws TwitterException 
	 */
	public void logout() throws TwitterException;

	/**
	 * つぶやき
	 * @param message メッセージ
	 * @throws TwitterException
	 */
	public void tweat(String message) throws TwitterException;


}
