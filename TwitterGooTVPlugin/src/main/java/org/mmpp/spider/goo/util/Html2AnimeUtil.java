package org.mmpp.spider.goo.util;

import java.util.List;



/**
 * HTMLを解析ツール
 * @author kou
 *
 */
public class Html2AnimeUtil {

	/**
	 * ソース内容を解析します
	 * @param source ページ内容
	 * @return
	 */
	public static List<TelevisionProgram> parse(String source) {
		List<TelevisionProgram> results = new java.util.LinkedList<TelevisionProgram>();
		boolean isTramsform=false;
		int intParseLineCount = 0;
		String[] lines = source.split(System.getProperty("line.separator"));
//		System.out.println("parser " + lines.length + " lines. " );
		// 行内容を繰り返す
		for(String line : lines){
			// 解析中であるかの判断
			if(!isTramsform){
				// 解析開始行を調査する
				isTramsform =line.startsWith("<table class=\"t01\"");
				continue;
			}
			// テーブルの終了は行の終了
			if(line.startsWith("</table>"))
				break;
			intParseLineCount++;
			// ヘッダーは無視
			if(intParseLineCount==1){
				continue;
			}
			TelevisionProgram program =castLineToTelevisionProgram(line);
			if(program!=null)
				results.add(program);
		}
		return results;
	}

	public static TelevisionProgram castLineToTelevisionProgram(String line) {
		java.util.regex.Pattern pt = java.util.regex.Pattern.compile(".+<td[^>]*>(.+?)</td><td[^>]*><a[^>]*>(.+?)</a></td><td[^>]*>(.+?)</td><td[^>]*>(.+?)</td>.+");
		java.util.regex.Matcher matcher = pt.matcher(line);
		if(!matcher.find())
			return null;

		String datetime = matcher.group(1);
		String title = matcher.group(2);
		String channel = matcher.group(3);
		String genre = matcher.group(4);

		// タイトルのタグを削除する
		{
			java.util.regex.Matcher titleMatcher = java.util.regex.Pattern.compile("(<[^>]+>)").matcher(title);
			title = titleMatcher.replaceAll("");

		}

		// 日付のエスケープ文字を変換する
		{
			datetime = datetime.replaceAll("&nbsp;", " ");
		}
		return TelevisionProgram.valueOf(datetime, title, channel, genre);
	}

}
