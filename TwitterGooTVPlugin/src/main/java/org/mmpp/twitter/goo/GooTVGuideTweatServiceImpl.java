package org.mmpp.twitter.goo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.mmpp.spider.goo.util.TelevisionProgram;

/**
 * Gooの最新情報からTweatメッセージを生成します
 * @author kou
 *
 */
public class GooTVGuideTweatServiceImpl implements GooTVGuideTweatService {

	@Override
	public List<String> getTweatMessages() {
		System.out.println("hello GooTVGuideTweatServiceImpl");
		List<String> messages = new java.util.LinkedList<String>();
		
		String prefix = "[番組表]";
		try {
			for(TelevisionProgram tvProgram : getNewAnimeProgurams()){
				if(getCache().contains(tvProgram))
					continue;
			   String message = castTelevisionProgram2Message(tvProgram);
				messages.add(prefix+"[新]"+message);
			}
			for(TelevisionProgram tvProgram : getEndAnimeProgurams()){
				if(getCache().contains(tvProgram))
					continue;
			   String message = castTelevisionProgram2Message(tvProgram);
				messages.add(prefix+"[終]"+message);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO 内部キャッシュを比較して、既に出した情報は排除する

		return messages;
	}

	/**
	 * アニメ新番組情報を取得します<br>
	 * http://tv.goo.ne.jp/search/result.php?genres%5B%5D=IA%7CIB&category=all&details%5B%5D=%5B%BF%B7%5D&key=&=%A1%A1%A1%A1%B8%A1%BA%F7%A1%A1%A1%A1
	 * @return アニメの新番組情報一覧
	 * @throws IOException 
	 */
	public List<TelevisionProgram> getNewAnimeProgurams() throws IOException {
		return getNewAnimeProgramParser().parse();
	}

	/**
	 * アニメの最終回情報一覧を取得します<br>
	 * http://tv.goo.ne.jp/search/result.php?genres%5B%5D=IA%7CIB&category=all&details%5B%5D=%5B%BA%C7%BD%AA%B2%F3%5D%7C%5B%BD%AA%5D&key=&=%A1%A1%A1%A1%B8%A1%BA%F7%A1%A1%A1%A1
	 * @return アニメの最終回情報一覧
	 * @throws IOException 
	 */
	public List<TelevisionProgram> getEndAnimeProgurams() throws IOException {
		return getEndAnimeProgramParser().parse();
	}

	/**
	 * 新番組パーサー
	 */
	private AnimeProgramParser _newAnimeProgramParser = null;
	public AnimeProgramParser getNewAnimeProgramParser(){
		if(_newAnimeProgramParser==null){
			try {
				String url = "http://tv.goo.ne.jp/search/result.php?genres%5B%5D=IA%7CIB&category=all&details%5B%5D=%5B%BF%B7%5D&key=&=%A1%A1%A1%A1%B8%A1%BA%F7%A1%A1%A1%A1";
				_newAnimeProgramParser = new AnimeProgramParserImpl(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _newAnimeProgramParser;
	}
	public void setNewAnimeProgramParser(AnimeProgramParser animeProgramParser){
		_newAnimeProgramParser =  animeProgramParser;
	}
	
	/**
	 * 最終回パーサー
	 */
	private AnimeProgramParser _endAnimeProgramParser = null;
	public AnimeProgramParser getEndAnimeProgramParser(){
		if(_endAnimeProgramParser==null){
			try {
				String url = "http://tv.goo.ne.jp/search/result.php?genres%5B%5D=IA%7CIB&category=all&details%5B%5D=%5B%BA%C7%BD%AA%B2%F3%5D%7C%5B%BD%AA%5D&key=&=%A1%A1%A1%A1%B8%A1%BA%F7%A1%A1%A1%A1";
				_endAnimeProgramParser = new AnimeProgramParserImpl(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _endAnimeProgramParser;
	}
	public void setEndAnimeProgramParser(AnimeProgramParser animeProgramParser){
		_endAnimeProgramParser =  animeProgramParser;
	}

	
	private List<String> castTelevisionProgram2Messages(List<TelevisionProgram> tvPrograms){
			List<String> messages = new java.util.LinkedList<String>();
			for(TelevisionProgram tvProgram : tvPrograms){
			String message = castTelevisionProgram2Message(tvProgram);
			if(message.length()>0)
				messages.add(message);
		}

		System.out.println(" searchAnimeProgurams : " + messages.size()+" messages. ");
		return messages;
	}

	private String castTelevisionProgram2Message(TelevisionProgram tvProgram) {
		if(tvProgram==null)
			return "";
		StringBuffer bufMessage = new StringBuffer();
		bufMessage.append(tvProgram.getDatetime());
		bufMessage.append(" ");
		bufMessage.append(tvProgram.getChannel());
		bufMessage.append(" ");
		bufMessage.append(tvProgram.getTitle());
		System.out.println(" castTelevisionProgram2Message ( " + tvProgram.toString() +" ) " );
		System.out.println(" castTelevisionProgram2Message return : " + bufMessage.toString());
		return bufMessage.toString();
	}
	AnimeProgramParser _animeProgramPerser;
	@Override
	public void setUrlParser(AnimeProgramParser animeProgramPerser) {
		_animeProgramPerser = animeProgramPerser;
	}
	public AnimeProgramParser getUrlParser() {
		return _animeProgramPerser;		
	}

	private AnimeTweatCache _animeTweatCacheInterface;
	@Override
	public void setCacheInterface(AnimeTweatCache animeTweatCache) {
		_animeTweatCacheInterface = animeTweatCache;
		_cache = null;
	}
	public AnimeTweatCache getCacheInterface() {
		return _animeTweatCacheInterface;
	}
	java.util.List<TelevisionProgram> _cache = null;
	public java.util.List<TelevisionProgram> getCache(){
		if(_cache==null){
			_cache = new java.util.LinkedList<TelevisionProgram>();
			if(getCacheInterface()!=null){
				_cache = getCacheInterface().getData();
			}
		}
		return _cache;
	}

}
