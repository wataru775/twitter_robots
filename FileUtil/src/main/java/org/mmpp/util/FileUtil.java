package org.mmpp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ファイル関連ツール
 * @author kou
 *
 */
public class FileUtil {

	/**
	 * ファイルを読み込みます
	 * @param file ファイル
	 * @return ファイル内容
	 */
	public static String readFile(File file) {
		StringBuffer source = new StringBuffer();
		java.io.BufferedReader reader = null;
		try{
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file)));
			String line;
			while(( line = reader.readLine())!=null){
				source.append(line);
				source.append(System.getProperty("line.separator"));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return source.toString();
	}

	/**
	 * StringからInputStreamを生成します
	 * @param source 
	 * @return
	 */
	public static InputStream readString(String source) {
		java.io.InputStream is = new java.io.ByteArrayInputStream(source.getBytes()) ;
		return is;
	}

}
