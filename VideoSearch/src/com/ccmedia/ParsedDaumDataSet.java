package com.ccmedia;

import com.ccmedia.parse.daum.model.entity.Rss;


public class ParsedDaumDataSet {
	
	private Rss rss;
	
	public ParsedDaumDataSet(){
		rss = new Rss();
	}
	
	public Rss getRss() {
		return rss;
	}

	public void setRss(Rss rss) {
		this.rss = rss;
	}

	@Override
	public String toString() {
		return "ParsedDaumDataSet [rss=" + rss + "]";
	}
	
	
	
}