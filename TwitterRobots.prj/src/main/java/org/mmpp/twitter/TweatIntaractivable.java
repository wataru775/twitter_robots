package org.mmpp.twitter;

public interface TweatIntaractivable extends org.mmpp.twitter.api.TweatMessageService {
	/**
	 * メッセージを取得できるサービスを格納する
	 * @param tweatReaderable サービス
	 */
	public void setTweatReaderable(TweatReaderable tweatReaderable);

}
