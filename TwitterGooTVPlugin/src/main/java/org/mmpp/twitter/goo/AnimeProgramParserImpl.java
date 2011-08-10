package org.mmpp.twitter.goo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * アニメ番組情報解析実装クラス
 * @author mmpp wataru
 * @since 0.0.1
 */
public class AnimeProgramParserImpl extends AbstractAnimeProgramParser{
	/**
	 * デフォルトコンストラクタ
	 */
	public AnimeProgramParserImpl(){
		super();
	}
	/**
	 * コンストラクタ
	 * @param url 抽出対象URL
	 * @throws MalformedURLException
	 */
	public AnimeProgramParserImpl(String url) throws MalformedURLException {
		this();
		setUrl(url);
	}
	/**
	 * 解析対象URL格納変数
	 */
	private java.net.URL _url;
	@Override
	public URL getUrl() {
		return _url;
	}
	/**
	 * 解析対象URLを格納します
	 * @param url 解析対象URL
	 * @throws MalformedURLException
	 */
	public void setUrl(String url) throws MalformedURLException{
		_url = new java.net.URL(url);		
	}
}
