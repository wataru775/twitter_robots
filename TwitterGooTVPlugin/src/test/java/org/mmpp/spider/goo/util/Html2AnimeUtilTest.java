package org.mmpp.spider.goo.util;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.mmpp.util.FileUtil;
import org.mmpp.spider.goo.util.Html2AnimeUtil;
import org.mmpp.spider.goo.util.TelevisionProgram;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;

public class Html2AnimeUtilTest {

	@Test
	public void testParseNewanime() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException{
		String source = FileUtil.readFile(new java.io.File("test/goo/newanime.html"));
		java.util.List<TelevisionProgram> results = Html2AnimeUtil.parse(source);  
		assertEquals("番組数が取得できること",12, results.size());
	}
	@Test
	public void testParseEndanime() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException{
		String source = FileUtil.readFile(new java.io.File("test/goo/endanime.html"));
		java.util.List<TelevisionProgram> results = Html2AnimeUtil.parse(source);
		assertNotNull(results);
		assertEquals("番組数が取得できること",1, results.size());
		TelevisionProgram program = results.get(0);
		assertNotNull(program);
		assertEquals("番組名が取得できること","楽しいムーミン一家 冒険日記", program.getTitle());
		assertEquals("放送日時が取得できること","12/30（木） 07:30〜08:00", program.getDatetime());
		assertEquals("放送局が取得できること","京都テレビ[34]", program.getChannel());
		
	}
	
	@Test
	public void testParseLine(){
		String line = "<tr><td nowrap>12/30（木）&nbsp;07:30〜08:00</td><td><a href=\"/contents/program/025/0508/20101230_0730/index.html\"><img src=\"/tv/img/icon/shinban.gif\" alt=\"新番組\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\">楽しいムーミン一家 冒険日記</a></td><td>京都テレビ[34]</td><td>アニメ／特撮</td></tr>";
		TelevisionProgram program = Html2AnimeUtil.castLineToTelevisionProgram(line);
		assertNotNull(program);
		assertEquals("番組名が取得できること","楽しいムーミン一家 冒険日記", program.getTitle());
		assertEquals("放送日時が取得できること","12/30（木） 07:30〜08:00", program.getDatetime());
		assertEquals("放送局が取得できること","京都テレビ[34]", program.getChannel());
	}
	@Test
	public void testParseLine3(){
		String line = "<tr><td nowrap>12/30（木）&nbsp;07:30〜08:00</td><td><a href=\"/contents/program/025/0508/20101230_0730/index.html\"><img src=\"/tv/img/icon/shinban.gif\" alt=\"新番組\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\">楽しいムーミン一家 冒険日記<img src=\"/tv/img/icon/sai.gif\" alt=\"再放送\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\"><img src=\"/tv/img/icon/saisyu.gif\" alt=\"最終回\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\"></a></td><td>京都テレビ[34]</td><td>アニメ／特撮</td></tr>";
		TelevisionProgram program = Html2AnimeUtil.castLineToTelevisionProgram(line);
		assertNotNull(program);
		assertEquals("番組名が取得できること","楽しいムーミン一家 冒険日記", program.getTitle());
		assertEquals("放送日時が取得できること","12/30（木） 07:30〜08:00", program.getDatetime());
		assertEquals("放送局が取得できること","京都テレビ[34]", program.getChannel());
	}
	@Test
	public void testParseLine2(){
		String line = "<tr><td nowrap>12/30（木）&nbsp;07:30〜08:00</td><td><a href=\"/contents/program/025/0508/20101230_0730/index.html\">楽しいムーミン一家 冒険日記<img src=\"/tv/img/icon/sai.gif\" alt=\"再放送\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\"><img src=\"/tv/img/icon/saisyu.gif\" alt=\"最終回\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\"></a></td><td>京都テレビ[34]</td><td>アニメ／特撮</td></tr>";
		TelevisionProgram program = Html2AnimeUtil.castLineToTelevisionProgram(line);
		assertNotNull(program);
		assertEquals("番組名が取得できること","楽しいムーミン一家 冒険日記", program.getTitle());
		assertEquals("放送日時が取得できること","12/30（木） 07:30〜08:00", program.getDatetime());
		assertEquals("放送局が取得できること","京都テレビ[34]", program.getChannel());
	}
	@Test
	public void testParseLine1(){
		String line = "<tr><td nowrap>12/30（木）&nbsp;07:30〜08:00</td><td><a href=\"/contents/program/025/0508/20101230_0730/index.html\">楽しいムーミン一家 冒険日記<img src=\"/tv/img/icon/sai.gif\" alt=\"再放送\" hspace=\"1\" width=\"13\" height=\"13\" border=\"0\"></a></td><td>京都テレビ[34]</td><td>アニメ／特撮</td></tr>";
		TelevisionProgram program = Html2AnimeUtil.castLineToTelevisionProgram(line);
		assertNotNull(program);
		assertEquals("番組名が取得できること","楽しいムーミン一家 冒険日記", program.getTitle());
		assertEquals("放送日時が取得できること","12/30（木） 07:30〜08:00", program.getDatetime());
		assertEquals("放送局が取得できること","京都テレビ[34]", program.getChannel());
	}
}
