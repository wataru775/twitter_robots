package org.mmpp.twitter.api;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterImpl implements org.mmpp.twitter.api.Twitter {

	/**
	 * twitter API内部格納変数
	 */
	private Twitter _twitter=null;

	/**
	 * Twitter APIを格納します
	 * @param twitter Twitter API
	 */
	private void setTwitter(Twitter twitter){
		_twitter = twitter; 	
	}
	/**
	 * Twitter APIを取得します
	 * @return Twitter API
	 */
	private Twitter getTwitter(){
		return _twitter;
	}
	
	/**
	 * ログインします
	 */
	@Override
	public void login() throws TwitterException {
		Twitter twitter = buildTwitter();
		setTwitter(twitter);
	}
	/**
	 * Twitterを生成します
	 * @return TwitterAPIを生成する。
	 * @throws TwitterException 
	 */
	private Twitter buildTwitter() throws TwitterException{
	    // Twitter Factoryを生成します。
		TwitterFactory twitterFactory = new TwitterFactory();
		Twitter twitter = twitterFactory.getInstance( );

	    return twitter;
	}

	/**
	 * メッセージをツイートします
	 */
	@Override
	public void tweat(String message) throws TwitterException {
		if(getTwitter()==null)
			throw new TwitterException("ログインしてません！");
		getTwitter().updateStatus(message);
	}

	@Override
	public void logout() {
		if(getTwitter()==null)
			return;
		getTwitter().shutdown();
		setTwitter(null);
	}

	@Override
	public ResponseList<Status> tweats() throws TwitterException {
		return getTwitter().getUserTimeline();
	}
	@Override
	public ResponseList<DirectMessage> getDirectMessages() {
		// TODO Auto-generated method stub
		return null;
	}
}
