package org.mmpp.twitter.goo;

import org.mmpp.spider.goo.util.TelevisionProgram;

/**
 * つぶやきキャッシュサービスインタフェイス
 * @author mmpp wataru
 * @since 0.0.1
 */
public interface AnimeTweatCache {
	/**
	 * 既につぶやいた番組情報を取得します
	 * @return 番組情報
	 */
	public java.util.List<TelevisionProgram> getData();
}
