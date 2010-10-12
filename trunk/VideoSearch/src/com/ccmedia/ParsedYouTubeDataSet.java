package com.ccmedia;

import com.ccmedia.parse.youTube.model.entity.Feed;

public class ParsedYouTubeDataSet {
	
	private Feed feed;
	
	public ParsedYouTubeDataSet(){
		feed = new Feed();
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	@Override
	public String toString() {
		return "ParsedYouTubeDataSet [feed=" + feed + "]";
	}
	
	
	
}