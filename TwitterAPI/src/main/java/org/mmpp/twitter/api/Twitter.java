package org.mmpp.twitter.api;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Twitter アプリケーションインターフェイス
 * @author kou
 *
 */
public interface Twitter extends TweatService {

	/**
	 * メッセージをTweatします
	 * @param message メッセージ
	 * @throws TwitterException 
	 */
	public void tweat(String message) throws TwitterException;


	/**
	 * つぶやきを取得します
	 * @return
	 * @throws TwitterException 
	 */
	public ResponseList<Status> tweats() throws TwitterException;

	/**
	 * ダイレクトメッセージ一覧を取得します
	 */
	public ResponseList<DirectMessage>  getDirectMessages();

}
