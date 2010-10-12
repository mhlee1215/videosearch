package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class GdComments extends basicXMLEntry{
	private GdFeedLink gdGeedLink;

	public GdComments(){
		gdGeedLink = new GdFeedLink();
	}
	
	public GdFeedLink getGdGeedLink() {
		return gdGeedLink;
	}

	public void setGdGeedLink(GdFeedLink gdGeedLink) {
		this.gdGeedLink = gdGeedLink;
	}

	@Override
	public String toString() {
		return "GdComments [gdGeedLink=" + gdGeedLink + "]";
	}
}
