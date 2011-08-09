package org.mmpp.twitter;

import java.util.List;

import twitter4j.Status;

/**
 * つぶやき読み込みインターフェイス
 */
public interface TweatReaderable {

	/**
	 * 最新の呟かれメッセージ一覧を読み込みます
	 * @return 呟かれメッセージ一覧
	 */
	public List<Status> readMentions();
	/**
	 * 呟かれメッセージ一覧を読み込みます
	 * @return 呟かれメッセージ一覧
	 */
	public List<Status> readMentionsAll();
	
}
