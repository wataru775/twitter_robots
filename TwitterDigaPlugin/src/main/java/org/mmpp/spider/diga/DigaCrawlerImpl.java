package org.mmpp.spider.diga;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.mmpp.spider.diga.util.MD5Sum;
import org.mmpp.spider.diga.util.MD5SumImpl;
import org.mmpp.spider.util.CrawlerUtil;


public class DigaCrawlerImpl implements DigaCrawler {

	private MD5Sum _md5Sum = new MD5SumImpl();

	String _hostname = null;
	
	@Override
	public void setHostName(String hostname) {
		_hostname = hostname;
	}
	public String getHostName() {
		return _hostname;
	}
	public java.net.URL getTopUrl() throws MalformedURLException{
		 return new java.net.URL("http://"+getHostName());
	}
	/**
	 * パスワード認証Actionページ
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getLoginPasswordURL() throws MalformedURLException {
		return new java.net.URL("http://"+getHostName() + "/cgi-bin/loginPsWd.cgi");
	}
	/**
	 * メニューの番組一覧のURLを取得する
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getMenuListURL()  throws MalformedURLException {
		return new java.net.URL("http://"+getHostName() + "/cgi-bin/dispframe.cgi?DISP_PAGE=6001&Radio_Drive=1");
	}
	/**
	 * メニューの番組一覧のURLを取得する
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getMenuRecorderURL()  throws MalformedURLException {
		return new java.net.URL("http://"+getHostName() + "/cgi-bin/dispframe.cgi?DISP_PAGE=1001");
	}
	/**
	 * ログアウト
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getMenuLogoutURL()  throws MalformedURLException {
		return new java.net.URL("http://"+getHostName() + "/cgi-bin/dispframe.cgi?DISP_PAGE=801");
	}
	private URL getShowReservedPage() throws MalformedURLException {
		return new java.net.URL("http://"+getHostName() + "/cgi-bin/dvdr/dvdr_ctrl.cgi");
	}
	@Override
	public void login(String password) {
			java.net.URL url;
			try {
				url = getTopUrl();
				URL loginUrl = getLoginPage(url);
				login(loginUrl,password);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private boolean login(URL url,String password){
		String pageSource = webPageSource(url);
		java.util.regex.Pattern pt = java.util.regex.Pattern.compile("var nonce=\"(.+?)\";");
		java.util.regex.Matcher matcher = pt.matcher(pageSource);
		if(!matcher.find())
			return false;

		String nonce = matcher.group(1);

		// b64digestを算出...
		String b64digest = _md5Sum.b64digest(nonce, password);
		
		// ログインスクリプトを表示する... //
		try {
			URL loginURL = getLoginPasswordURL();
			java.net.URLConnection uc = loginURL.openConnection();
			// method = POST
			uc.setDoOutput(true);
			StringBuffer bufRequest = new StringBuffer();
			bufRequest.append("nonce="+ nonce);
			bufRequest.append("&");
			bufRequest.append("digest="+ b64digest);

			java.io.PrintStream writer = null;
			try{
				writer = new java.io.PrintStream(uc.getOutputStream());
				writer.print(bufRequest.toString());
			}finally{
				if(writer!=null)
					writer.close();
			}
			String logingResultPage = getWebPageSource(uc);
			// TODO ログイン結果を判断する
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private URL getLoginPage(URL url) throws MalformedURLException {
		String pageSource = webPageSource(url);
		java.util.regex.Pattern pt = java.util.regex.Pattern.compile(".+ src='(/cgi-bin/prevLogin.cgi.+?)' .+");
		java.util.regex.Matcher matcher = pt.matcher(pageSource);
		if(!matcher.find())
			return null;

		return new java.net.URL(getTopUrl()+matcher.group(1));

	}
	/**
	 * Webページ内容を取得します
	 * @param urlConnection
	 * @return ページソース
	 */
	private String getWebPageSource(URLConnection urlConnection) {
		return webPageSource(urlConnection);
	}
	@Override
	public String status() {

		StringBuffer bufStatus =  new StringBuffer();

		String recording = getRecordingCount();
		bufStatus.append("録画数 : ");
		bufStatus.append(recording);
		bufStatus.append(" 残容量 : ");
		bufStatus.append(getRecordableTime());
//		bufStatus.append(" 予約数 : ");
//		bufStatus.append(getReservedCount());
		
		return bufStatus.toString();
	}
	/**
	 * 残時間取得ロジック
	 * @return
	 */
	private String getRecordableTime() {
		try {
			return getWebPageMatchValue(getMenuRecorderURL(),".+<b>(\\d+:\\d+?)</b>.+");
		} catch (MalformedURLException e) {
		}
		return null;
	}
	/**
	 * 録画番組数を取得します
	 * @return
	 */
	private String getRecordingCount(){
		String strRecordingCount = null;
		try {
			strRecordingCount = getWebPageMatchValue(getMenuListURL(),".+番組数：&nbsp;(\\d.+?)</font>.+",true);
		} catch (MalformedURLException e) {
		}
		return strRecordingCount;
	}
	/**
	 * 予約数を取得する
	 * @return
	 */
	private String getReservedCount() {
		try {
			java.net.URL url = getShowReservedPage();
			java.net.URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			
			String request = "cCMD_RSVLST.x=52&cCMD_RSVLST.y=20";
			java.io.PrintStream writer = null;
			try{
				writer = new java.io.PrintStream(urlConnection.getOutputStream());
				writer.print(request);
			}finally{
				if(writer!=null)
					writer.close();
			}

			String resultPage = getWebPageSource(urlConnection);

			System.out.println(resultPage);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @param url
	 * @param paternRegx
	 * @param redirect 待ち時間が存在する場合リダイレクトを解析します
	 * @return
	 */
	private String getWebPageMatchValue(java.net.URL url,String paternRegx,boolean redirect){
		String pageSource = webPageSource(url);
		String webPageMatchValue = getWebPageMatchValue(pageSource,paternRegx);
		if(!redirect||webPageMatchValue!=null)
			return webPageMatchValue;
		
		// リダイレクト、待ち時間解析... //
		// System.out.println(pageSource);
		// 通信中であるかの判断
		// 15秒に一回の再接続を行う。
		while(isConnectingWait(pageSource)){
			try {
				Thread.sleep(15 * 1000);
			} catch (InterruptedException e) {
			}
			// ページを取得
			pageSource = webPageSource(url);
		}
		webPageMatchValue = getWebPageMatchValue(pageSource,paternRegx);
		return webPageMatchValue;
	}
	
	/**
	 * 通信中の結果ページであるかの判断
	 * @param pageSource 結果ページ
	 * @return 判断フラグ (true : 待ちページ)
	 */
	private boolean isConnectingWait(String pageSource) {
		java.util.regex.Pattern pattern = Pattern.compile("DIGAと通信中です。しばらくお待ちください。<br>数十秒かかることがあります。<br></font></td>");
		java.util.regex.Matcher matcher = pattern.matcher(pageSource);
		return matcher.find();
	}
	/**
	 * URL結果の正規表現文字列を取得します
	 * @param url
	 * @param paternRegx
	 * @return
	 */
	private String getWebPageMatchValue(java.net.URL url,String paternRegx){
		String pageSource = webPageSource(url);
		return getWebPageMatchValue(pageSource,paternRegx);
	}
	private String getWebPageMatchValue(String pageSource,String paternRegx){
		java.util.regex.Pattern pt = java.util.regex.Pattern.compile(paternRegx);
		java.util.regex.Matcher matcher = pt.matcher(pageSource);
		if(!matcher.find())
			return null;

		return matcher.group(1);
		
	}
	@Override
	public void logout() {

		try {
			webPageSource(getMenuLogoutURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String webPageSource(URL url) {
		return	CrawlerUtil.webPageSource(url,"SHIFT-JIS");
	}
	private String webPageSource(URLConnection urlConnection) {
		return	CrawlerUtil.webPageSource(urlConnection,"SHIFT-JIS");
	}

}
