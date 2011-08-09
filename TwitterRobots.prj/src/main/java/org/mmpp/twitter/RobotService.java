package org.mmpp.twitter;
/**
 * ロボットサービスインターフェイス
 * @author kou
 *
 */
public interface RobotService {

	/**
	 * サービスを開始します
	 */
	public void start();
	
	/**
	 * サービスを停止します
	 */
	public void stop();
	
	/**
	 * サービス状態を取得します
	 * @return
	 */
	public String getStatus();
}
