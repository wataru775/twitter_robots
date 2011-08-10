package org.mmpp.twitter.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.mmpp.spider.goo.util.TelevisionProgram;
import org.mmpp.twitter.goo.AnimeProgramParser;
import org.mmpp.twitter.goo.AnimeTweatCache;
import org.mmpp.twitter.goo.GooTVGuideTweatService;
import org.mmpp.twitter.goo.GooTVGuideTweatServiceImpl;


import static org.junit.Assert.*;

public class GooTVGuideTweatServiceImplTest {

	GooTVGuideTweatService service = new GooTVGuideTweatServiceImpl();
	@Test
	public void testGetTweatMessages(){
		service.setNewAnimeProgramParser(new AnimeProgramParser() {
			
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				results.add(TelevisionProgram.valueOf("2/27（日） 01:00〜01:30", "フラクタル", "BSフジ[8ch]", "アニメ／特撮"));
				results.add(TelevisionProgram.valueOf("2/27（日） 01:30〜02:00", "放浪息子", "BSフジ[8ch]", "アニメ／特撮"));
				return results;
			}
		});
		service.setEndAnimeProgramParser(new AnimeProgramParser() {
			
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				TelevisionProgram tvProgram = TelevisionProgram.valueOf("2/27（日） 02:58〜03:28", "みつどもえ増量中!", "毎日放送[4]", "アニメ／特撮");
				results.add(tvProgram);
				return results;
			}
		});
		List<String> results =service.getTweatMessages();
		assertNotNull(results);
		assertEquals(3,results.size());
		
	}
	@Test
	public void testGetNewAnimeProgurams() throws IOException{
		service.setNewAnimeProgramParser(new AnimeProgramParser() {
			
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				results.add(TelevisionProgram.valueOf("2/27（日） 01:00〜01:30", "フラクタル", "BSフジ[8ch]", "アニメ／特撮"));
				results.add(TelevisionProgram.valueOf("2/27（日） 01:30〜02:00", "放浪息子", "BSフジ[8ch]", "アニメ／特撮"));
				return results;
			}
		});
		List<TelevisionProgram> results = service.getNewAnimeProgurams();
		assertNotNull(results);
		assertEquals(2,results.size());
	}
	@Test
	public void testGetEndAnimeProgurams() throws IOException{
		service.setEndAnimeProgramParser(new AnimeProgramParser() {
			
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				TelevisionProgram tvProgram = TelevisionProgram.valueOf("2/27（日） 02:58〜03:28", "みつどもえ増量中!", "毎日放送[4]", "アニメ／特撮");
				results.add(tvProgram);
				return results;
			}
		});
		List<TelevisionProgram> results = service.getEndAnimeProgurams();
		assertNotNull(results);
		assertEquals(1,results.size());
	}
	
	@Test
	public void testCacheTweat(){
		service.setNewAnimeProgramParser(new AnimeProgramParser() {
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				results.add(TelevisionProgram.valueOf("2/27（日） 01:00〜01:30", "フラクタル", "BSフジ[8ch]", "アニメ／特撮"));
				results.add(TelevisionProgram.valueOf("2/27（日） 01:30〜02:00", "放浪息子", "BSフジ[8ch]", "アニメ／特撮"));
				return results;
			}
		});
		service.setEndAnimeProgramParser(new AnimeProgramParser() {
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				TelevisionProgram tvProgram = TelevisionProgram.valueOf("2/27（日） 02:58〜03:28", "みつどもえ増量中!", "毎日放送[4]", "アニメ／特撮");
				results.add(tvProgram);
				return results;
			}
		});
		// キャッシュを格納する
		service.setCacheInterface(new AnimeTweatCache(){
				public java.util.List<TelevisionProgram> getData(){
					List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
					results.add(TelevisionProgram.valueOf("2/27（日） 01:00〜01:30", "フラクタル", "BSフジ[8ch]", "アニメ／特撮"));
					results.add(TelevisionProgram.valueOf("2/27（日） 01:30〜02:00", "放浪息子", "BSフジ[8ch]", "アニメ／特撮"));
					results.add(TelevisionProgram.valueOf("2/27（日） 02:58〜03:28", "みつどもえ増量中!", "毎日放送[4]", "アニメ／特撮"));
					return results;
				}});
		List<String> results =service.getTweatMessages();
		assertNotNull(results);
		assertEquals(0,results.size());	
	}
	@Test
	public void testCachedProgramTweat(){
		service.setNewAnimeProgramParser(new AnimeProgramParser() {
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				results.add(TelevisionProgram.valueOf("2/27（日） 01:00〜01:30", "フラクタル", "BSフジ[8ch]", "アニメ／特撮"));
				results.add(TelevisionProgram.valueOf("2/27（日） 01:30〜02:00", "放浪息子", "BSフジ[8ch]", "アニメ／特撮"));
				return results;
			}
		});
		service.setEndAnimeProgramParser(new AnimeProgramParser() {
			@Override
			public List<TelevisionProgram> parse() throws IOException {
				List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
				TelevisionProgram tvProgram = TelevisionProgram.valueOf("2/27（日） 02:58〜03:28", "みつどもえ増量中!", "毎日放送[4]", "アニメ／特撮");
				results.add(tvProgram);
				return results;
			}
		});
		// キャッシュを格納する
		service.setCacheInterface(new AnimeTweatCache(){
				public java.util.List<TelevisionProgram> getData(){
					List<TelevisionProgram>  results = new java.util.LinkedList<TelevisionProgram>();
					return results;
				}});
		List<String> results;
		// 初回はでるが
		results =service.getTweatMessages();
		// 二度目はでません。
		results =service.getTweatMessages();
		assertEquals(0,results.size());	
	}
}
