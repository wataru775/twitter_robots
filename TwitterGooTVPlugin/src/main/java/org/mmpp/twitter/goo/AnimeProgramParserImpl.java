package org.mmpp.twitter.goo;

import java.net.MalformedURLException;
import java.net.URL;

public class AnimeProgramParserImpl extends AbstractAnimeProgramParser{

	public AnimeProgramParserImpl(){
		super();
	}
	public AnimeProgramParserImpl(String url) throws MalformedURLException {
		this();
		setUrl(url);
	}

	java.net.URL _url;
	@Override
	public URL getUrl() {
		return _url;
	}

	public void setUrl(String url) throws MalformedURLException{
		_url = new java.net.URL(url);		
	}
}
