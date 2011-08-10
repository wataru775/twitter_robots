package org.mmpp.twitter.goo;

import java.io.IOException;
import java.util.List;

import org.mmpp.spider.goo.util.TelevisionProgram;
import org.mmpp.twitter.api.TweatMessageService;

/**
 * Gooの番組表Webページから番組情報を抽出するサービスインタフェイス
 * @author mmpp wataru
 * @since 0.0.1
 */
public interface GooTVGuideTweatService extends TweatMessageService {

	/**
	 * 新番組一覧を取得します
	 * @return 新番組一覧
	 * @throws IOException
	 */
	public List<TelevisionProgram> getNewAnimeProgurams() throws IOException;
	/**
	 * 最終回番組一覧を取得します
	 * @return 最終回番組一覧
	 * @throws IOException
	 */
	public List<TelevisionProgram> getEndAnimeProgurams() throws IOException;

	/**
	 * Web解析ツールを格納します
	 * @param animeProgramPerser Web解析ツール
	 */
	public void setUrlParser(AnimeProgramParser animeProgramPerser);

	/**
	 * 新番組解析ツールを格納します
	 * @param animeProgramParser 番組解析ツール
	 */
	public void setNewAnimeProgramParser(AnimeProgramParser animeProgramParser);
	/**
	 * 最終回解析ツールを格納します
	 * @param animeProgramParser 番組解析ツール
	 */
	public void setEndAnimeProgramParser(AnimeProgramParser animeProgramParser);
	/**
	 * キャッシング機能サービスを格納します
	 * @param animeTweatCache キャッシング機能サービス
	 */
	public void setCacheInterface(AnimeTweatCache animeTweatCache);
}
