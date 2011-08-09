package org.mmpp.twitter.diga;

import org.mmpp.spider.diga.DigaCrawler;
import org.mmpp.spider.diga.DigaCrawlerImpl;
import org.mmpp.twitter.api.TweatMessageService;

/**
 * DIGAの残り時間容量などをtweatするサービス
 * @author kou
 *
 */
public class DigaStatusTweatServiceImpl implements TweatMessageService{

	private DigaCrawler _digaCrawler = null;
	/**
	 * 端末ログインパスワード
	 */
	private String _loginPassword;
	/**
	 * 端末名称
	 */
	private String _terminalName;
	
	private String getTerminalName(){
		return _terminalName;
	}
	public DigaCrawler getDigaCrawler(){
		if(_digaCrawler==null){
			_digaCrawler = new DigaCrawlerImpl();
			// TODO 設定情報を格納するけどコンフィグとか考える
			_digaCrawler.setHostName("192.168.1.111");
			_loginPassword = "kou307";
			_terminalName = "DMR-BW800";
		}
		
		return _digaCrawler;
	}
	@Override
	public java.util.List<String> getTweatMessages() {
		java.util.List<String> messages = new java.util.LinkedList<String>();
		String status = null;
		DigaCrawler digaCrawler = getDigaCrawler();
		// ログインする
		digaCrawler.login(_loginPassword);
		try{
			status = digaCrawler.status();
		}finally{
			digaCrawler.logout();
		}

		if(status==null)
			status = "忙しいもしくは線が切れている？";
		
		messages.add( "[機器監視]機器:"+getTerminalName()+" " + status );
		
		return messages;
	}

}
