package org.mmpp.twitter.plugin.incubator;

import java.util.List;

import org.mmpp.twitter.api.TweatMessageService;

/**
 * 定型メッセージつぶやきサービス抽象クラス
 */
public class TweatMessagingService implements TweatMessageService{
	/**
	 * 発行メッセージのプロパティファイル名
	 */
	private final String propetiesFilename;
	/**
	 * 前回の発行時刻
	 */
	private long _lastPublishTime = -1;

	/**
	 * 内部格納メッセージ一覧
	 */
	private java.util.List<String> _storedMessages = null;
	/**
	 * 前回のメッセージ番号<br>
	 * 重複したメッセージが発生しないように
	 */
	private int _lastMessageNum = -1;

	/**
	 * 発行インターバル<br>
	 * メッセージ配信を行う間隔時間を格納します<br>
	 * default : 1時間
	 */
	private long _interval = 1 * 60 * 60 * 1000L;
	/**
	 * 発行メッセージの末尾提携文字列
	 */
	private String _messagePostFix="";
	/**
	 * 発行メッセージの先頭提携文字列
	 */
	private String _messagePreFix="";

	/**
	 * デフォルトコンストラクタ
	 * @param propetiesFilename プロパティファイル名
	 */
	public TweatMessagingService(String propetiesFilename){
		super();
		this.propetiesFilename = propetiesFilename;
	}
	/**
	 * メッセージ発行間隔時間を取得します
	 * @return メッセージ発行間隔時間[ms]
	 */
	public long getInterval(){
		return _interval;
	}
	/**
	 * メッセージ発行間隔時間を格納します
	 * @param interval メッセージ発行間隔時間
	 */
	public void setInterval(long interval){
		_interval = interval;
	}
	/**
	 * 内部格納メッセージ一覧を取得します
	 * @return 内部格納メッセージ一覧
	 */
	private java.util.List<String> getStoredMessages(){
		if(_storedMessages==null){
			_storedMessages = new java.util.LinkedList<String>();
			_storedMessages = readMessagesFromPropertiesFile(propetiesFilename);
		}
		return _storedMessages;
	}
	/**
	 * 指定したpropertiesファイルからメッセージ一覧を抽出します
	 * @param filename propertiesファイル ( Classpath )
	 * @return メッセージ一覧 ( KeySet )
	 */
	protected java.util.List<String> readMessagesFromPropertiesFile(String filename){
		java.util.List<String> results = new java.util.LinkedList<String>();
		java.util.Properties prop = new java.util.Properties();
		java.io.InputStreamReader readerStream = null;
		try {
			readerStream = new java.io.InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename));
			
			prop.load(readerStream);
			for(Object message : prop.keySet()){
				results.add((String)message);
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (readerStream != null) 
					readerStream.close();
			} catch (java.io.IOException e) {}
		}
		return results;
	}
	/**
	 * メッセージランダムでメッセージを取得します
	 * @return メッセージ
	 */
	private String readRandomMessage(){
		int nextNumber=readRandamNumber(getStoredMessages().size(),_lastMessageNum);
		_lastMessageNum = nextNumber;
		return getStoredMessages().get(nextNumber);
	}
	/**
	 * ランダムメッセージ番号を発生させます
	 * @param maxValue メッセージ数
	 * @param lastNumber 最終メッセージ番号
	 * @return メッセージ番号
	 */
	private int readRandamNumber(int maxValue,int lastNumber){
		int nextNumber=-1;
		java.util.Random randam = new java.util.Random();
		nextNumber = randam.nextInt(maxValue);
		if(lastNumber==nextNumber)
			return readRandamNumber(maxValue,lastNumber);
		return nextNumber;

	}

	/**
	 * メッセージを引き渡します
	 * @return メッセージ (null : 発行されません)
	 */
	protected String getReleaseMessage(){
		java.util.Date publishDate = new java.util.Date();
		if(publishDate.getTime() < _lastPublishTime + getInterval())
			return null;
		_lastPublishTime = publishDate.getTime();
		String message = readRandomMessage();
		// 例外処理
		{ // Twitterの文字制限140文字に整えます
			int maxMessageLength = 140-getMessagePostFix().length() - getMessagePreFix().length();
			if(message.length()>maxMessageLength)
				message = message.substring(0,maxMessageLength-3)+"...";
		}
		return getMessagePreFix() + message + getMessagePostFix();
	}
	/**
	 * 発行メッセージの末尾定型文字列を取得します
	 * @return 発行メッセージの末尾定型文字列
	 */
	private String getMessagePostFix() {
		return _messagePostFix;
	}
	/**
	 * 発行メッセージの末尾定型文字列を格納します
	 * @param messagePostFix 発行メッセージの末尾定型文字列
	 */
	public void setMessagePostfix(String messagePostFix) {
		_messagePostFix = messagePostFix;
	}
	/**
	 * 発行メッセージの先頭定型文字列を取得します
	 * @return 発行メッセージの先頭定型文字列
	 */
	private String getMessagePreFix() {
		return _messagePreFix;
	}
	/**
	 * 発行メッセージの先頭定型文字列を格納します
	 * @param messagePostFix 発行メッセージの先頭定型文字列
	 */
	public void setMessagePrefix(String messagePreFix) {
		_messagePreFix = messagePreFix;
	}

	@Override
	public List<String> getTweatMessages() {
		List<String> results  =new java.util.LinkedList<String>();
		String message = getReleaseMessage();
		if(message!=null){
			results.add(message);
		}
		return results;
	}

}
