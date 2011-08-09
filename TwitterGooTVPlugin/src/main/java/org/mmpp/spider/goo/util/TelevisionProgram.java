package org.mmpp.spider.goo.util;

/**
 * GooのTV番組検索結果の表示画面情報クラス
 * @author kou
 *
 */
public class TelevisionProgram implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 放送日時
	 */
	private String _datetime;
	/**
	 * 番組名
	 */
	private String _title;
	/**
	 * 放送局
	 */
	private String _channel;
	/**
	 * ジャンル
	 */
	private String _genre;

	/**
	 * 新番組フラグ
	 */
	private boolean _newProgram;
	/**
	 * 終了フラグ
	 */
	private boolean _endProgram;
	/**
	 * デフォルトコンストラクタ
	 */
	private TelevisionProgram(){
		super();
	}

	/**
	 * 放送日時を取得する
	 * @return 放送日時
	 */
	public String getDatetime() {
		return _datetime;
	}
	/**
	 * 放送日時を格納する
	 * @param datetime 放送日時
	 */
	private void setDatetime(String datetime){
		_datetime = datetime;
	}

	/**
	 * 番組名を取得する
	 * @return 番組名
	 */
	public String getTitle() {
		return _title;
	}
	/**
	 * 番組名を格納します
	 * @param title 番組名
	 */
	private void setTitle(String title){
		_title = title;
	}
	/**
	 * 放送局を取得する
	 * @return 放送局
	 */
	public String getChannel() {
		return _channel;
	}

	/**
	 * 放送局を格納します
	 * @param channel 放送局
	 */
	private void setChannel(String channel){
		_channel = channel;
	}
	/**
	 * ジャンルを取得する
	 * @return ジャンル
	 */
	public String getGenre() {
		return _genre;
	}
	/**
	 * ジャンルを格納する
	 * @param genre ジャンル
	 */
	private void setGenre(String genre) {
		_genre = genre;
	}

	public String toString(){
		return "TelevisionProgram(" +
			"datetime = " + getDatetime() +
			"," +
			"title = " + getTitle() +
			"," +
			"channel = " + getChannel() +
			"," +
			"genre = " + getGenre() +
			")";
	}


	public boolean isNewProgram() {
		return _newProgram;
	}

	public void setNewProgram(boolean newProgram) {
		this._newProgram = newProgram;
	}

	public boolean isEndProgram() {
		return _endProgram;
	}

	public void setEndProgram(boolean endProgram) {
		this._endProgram = endProgram;
	}

	/**
	 * 生成
	 * @param datetime
	 * @param title
	 * @param channel
	 * @param genre
	 * @return
	 */
	public static TelevisionProgram valueOf(String datetime,String title,String channel,String genre){
		TelevisionProgram tvProgram = new TelevisionProgram();
		tvProgram.setDatetime(datetime);
		tvProgram.setTitle(title);
		tvProgram.setChannel(channel);
		tvProgram.setGenre(genre);
		
		return tvProgram;
	}
	

	// http://java.sun.com/j2se/1.5.0/ja/docs/ja/api/java/util/Hashtable.html
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}
}
