package org.mmpp.twitter;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import twitter4j.TwitterException;

/**
 * Twitterの読み込みサービス実装クラス
 * @author kou
 *
 */
public class TweatReaderImpl implements TweatReaderable{
	/**
	 * 前回の読み込み時間を格納します
	 */
	private long _lastReadtime =-1;
	/**
	 * 内部キャッシュメッセージ
	 */
	private java.util.List<Status> _cacheMessages=null;

	/**
	 * 内部キャッシュメッセージを取得します
	 * @return 内部キャッシュメッセージ
	 */
	private java.util.List<Status> getCacheMessages(){
		if(_cacheMessages==null){
			_cacheMessages = new java.util.LinkedList<Status>();
		}
		return _cacheMessages;
	}

	@Override
	public List<Status> readMentions() {
		List<Status> results = new java.util.LinkedList<Status>();
		long nowtime = new java.util.Date().getTime();
		if(_lastReadtime+getInterval()>nowtime)
			return results;
		System.out.println("read");
		List<Status> messages = readMentionsAll();
		java.util.List<Status> cacheMessages = getCacheMessages();
		for(Status message:messages){
			if(!cacheMessages.contains(message)){
				results.add(message);
				cacheMessages.add(message);
			}

		}
		for(Status message:cacheMessages){
			if(!messages.contains(message)){
				cacheMessages.remove(message);
			}

		}
		_lastReadtime = nowtime;
		return results;
	}

	private long getInterval() {
		return 1 * 60 * 1000;
	}

	@Override
	public List<Status> readMentionsAll() {
		List<Status> results = new java.util.LinkedList<Status>();
		Twitter twitter = null;
		try{
			TwitterFactory twitterFactory = new TwitterFactory();
			twitter = twitterFactory.getInstance( );
			
			for(Status message : twitter.getMentions()){
					results.add(message);
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}finally{
			if(twitter!=null){
				twitter.shutdown();
			}
		}
		
		
		return results;
	}

}
