package org.mmpp.twitter.api.sample;

import org.mmpp.twitter.api.TweatService;

import twitter4j.TwitterException;
/**
 * 標準出力への出力サービス
 */
public class StandardOutputTweatService implements TweatService {
	@Override
	public void tweat(String message) throws TwitterException {
		System.out.println(" message : " + message);
	}
	@Override	
	public void logout() throws TwitterException {
		
	}
	@Override 
	public void login() throws TwitterException {
		
	}

}
