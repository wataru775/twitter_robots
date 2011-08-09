package org.mmpp.twitter.plugin.incubator;


/**
 * メッセージ生成サービス
 *
 */
public class QbayMessageService extends TweatMessagingService {
	/**
	 * デフォルトコンストラクタ
	 */
	public QbayMessageService() {
		super("qbay-message.properies");
		super.setMessagePostfix("(キュゥべえ)");
		super.setInterval(1000L);
	}
}
