package org.mmpp.twitter.api;
/**
 * ツイッターつぶやきメッセージ作成サービス
 * @author nagai
 *
 */
public interface TweatMessageService {
	/**
	 * メッセージを取得します
	 * @return 生成メッセージ一覧
	 */
	public java.util.List<String> getTweatMessages();

}
