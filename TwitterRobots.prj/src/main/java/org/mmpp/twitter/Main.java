package org.mmpp.twitter;

/**
 * 実行メインクラス
 * @author kou
 *
 */
public class Main {

	/**
	 * ロボットサービスを取得します
	 * @return
	 */
	private static RobotService getRobotService(){
		return new RobotServiceImpl();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RobotService service = getRobotService();

		service.start();
		System.out.println(service.getStatus());
	}

}
