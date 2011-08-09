package org.mmpp.twitter.goo;

import java.io.IOException;
import java.util.List;

import org.mmpp.spider.goo.util.TelevisionProgram;


public interface AnimeProgramParser {
	public List<TelevisionProgram> parse() throws IOException;
}
