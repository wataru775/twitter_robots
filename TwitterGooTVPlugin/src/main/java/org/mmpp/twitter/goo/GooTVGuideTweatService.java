package org.mmpp.twitter.goo;

import java.io.IOException;
import java.util.List;

import org.mmpp.spider.goo.util.TelevisionProgram;
import org.mmpp.twitter.api.TweatMessageService;


public interface GooTVGuideTweatService extends TweatMessageService {

	public List<TelevisionProgram> getNewAnimeProgurams() throws IOException;

	public List<TelevisionProgram> getEndAnimeProgurams() throws IOException;

	public void setUrlParser(AnimeProgramParser animeProgramPerser);


	public void setNewAnimeProgramParser(AnimeProgramParser animeProgramParser);

	public void setEndAnimeProgramParser(AnimeProgramParser animeProgramParser);

	public void setCacheInterface(AnimeTweatCache animeTweatCache);
}
