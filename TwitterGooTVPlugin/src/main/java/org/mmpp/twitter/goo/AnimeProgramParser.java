package org.mmpp.twitter.goo;

import java.io.IOException;
import java.util.List;

import org.mmpp.spider.goo.util.TelevisionProgram;

/**
 * アニメ番組情報解析インタフェイス
 * @author mmpp wataru
 * @since 0.0.1
 */
public interface AnimeProgramParser {
	/**
	 * アニメ情報を抽出します
	 * @return アニメ情報一覧
	 * @throws IOException
	 */
	public List<TelevisionProgram> parse() throws IOException;
}
