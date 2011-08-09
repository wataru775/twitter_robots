package org.mmpp.twitter;

import java.util.List;

import org.mmpp.twitter.api.TweatMessageService;
import org.mmpp.twitter.api.TweatService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import twitter4j.TwitterException;

public class RobotServiceProcess implements Runnable {
	/**
	 * ApplicationContext格納変数
	 */
	private ApplicationContext _applicationContext = null;

	/**
	 * 動作判断フラグ
	 * true : 停止中
	 */
	private boolean _shutdown = false;
	
	/**
	 * ApplicationContextを取得します
	 * @return ApplicationContext
	 */
	private ApplicationContext getApplicationContext(){
		if(_applicationContext==null){
			_applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return _applicationContext;
	}
	/**
	 * メッセージサービスプラグイン一覧を取得します
	 * @return メッセージサービスプラグイン一覧 
	 */
	private java.util.List<TweatMessageService> getMessagePlugins(){
		Object objValue = getApplicationContext().getBean("messages");
		@SuppressWarnings("unchecked")
		java.util.List<TweatMessageService> pluginLists = (java.util.List<TweatMessageService>)objValue;
		return pluginLists;
	}
	/**
	 * プロセスの起動判断を取得します
	 * @return 判断フラグ ( true : 停止中 )
	 */
	public boolean isShutdown(){
		return _shutdown;
	}
	/**
	 * 停止動作を変更します
	 * @param shutdown 判断フラグ(true : 停止 ) 
	 */
	public void setShutdown(boolean shutdown){
		_shutdown = shutdown;
	}
	/**
	 * 作動間隔時間を取得します
	 * @return 作動間隔時間
	 */
	public long getDelayInterval(){
//		return 1*60*1000L;
		return 1*1*1000L;
	}
	@Override
	public void run() {
		System.out.println("HELLO");
		System.out.println(" DelayInterval : " + getDelayInterval() + "[ms].");
		java.util.List<TweatMessageService> services = getMessagePlugins();
		System.out.println(" i have messaging " + services.size() + " services.");
		java.util.List<TweatService> tweatServices = getTweatPlugins();
		System.out.println(" i have tweat  " + tweatServices.size() + " services.");
		while(!isShutdown()){
			// メッセージサービスを実行します... //
			execute();

			// 休眠中
			try {
				Thread.sleep(getDelayInterval());
			} catch (InterruptedException e) {
			}
		}

	}
	/**
	 * 繰り返し実行処理
	 */
	private void execute() {
		java.util.List<TweatService> tweatServices = getTweatPlugins();
		// メッセージ一覧が取得されるはず... Twitter用の140文字以内 
		java.util.List<String> tweatMessages = getTweatMessages();
		for(TweatService tweatService : tweatServices){
//			Twitter twitter = getTwitter();
			try{
				tweatService.login();
				
				// メッセージtweat引き継ぎ
				for(String message : tweatMessages){
					try{
						tweatService.tweat(message);
					}catch(TwitterException e){
						if(e.getMessage().indexOf("Status is a duplicate")<0)
							throw e;
						// 重複メッセージエラーは既に解っています
					}
				}
//				ResponseList<Status> response = twitter.tweats();
//				System.out.println(" I get "+ response.size()+" tweats. ");
			} catch (TwitterException e) {
				e.printStackTrace();
			}finally{
				if(tweatService!=null){
					try {
						tweatService.logout();
					} catch (TwitterException e) {
						e.printStackTrace();
					}
				}
			}
		}		
	}
	/**
	 * ツイッターサービス一覧を取得します
	 * @return ツイッターサービス一覧
	 */
	private List<TweatService> getTweatPlugins() {
		Object objValue = getApplicationContext().getBean("tweats");
		@SuppressWarnings("unchecked")
		java.util.List<TweatService> pluginLists = (java.util.List<TweatService>)objValue;

		return pluginLists;
	}
	/**
	 * メッセージサービスを実行します
	 * @return メッセージ一覧
	 */
	private List<String> getTweatMessages() {
		List<String> messages = new java.util.LinkedList<String>();
		for(TweatMessageService services : getMessagePlugins()){
			messages.addAll(services.getTweatMessages());
		}
		
		return messages;
	}
}
