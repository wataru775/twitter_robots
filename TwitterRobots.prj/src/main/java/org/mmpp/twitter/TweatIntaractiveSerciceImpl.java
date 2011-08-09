package org.mmpp.twitter;

import java.util.List;

import twitter4j.Status;

/**
 * Twitterの関連に応答するサービス
 * @author kou
 *
 */
public class TweatIntaractiveSerciceImpl implements TweatIntaractivable {

	/**
	 * Twitter読み込みサービス
	 */
	private TweatReaderable _tweatReaderable;
	@Override
	public List<String> getTweatMessages() {
		java.util.List<String> results = new java.util.LinkedList<String>();
		for(Status retweet : getTweatReaderable().readMentions()){
			String message;
			message = " OK  RT ";
			message += retweet.getText();
			results.add(message);
		}
		return results;
	}

	@Override
	public void setTweatReaderable(TweatReaderable tweatReaderable) {
		_tweatReaderable = tweatReaderable;
	}

	private TweatReaderable getTweatReaderable(){
		return _tweatReaderable;
	}
}
