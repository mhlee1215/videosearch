package com.ccmedia.parse.youTube.model.entity;

public class GdFeedLink {
	private String href;
	private int countHint;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getCountHint() {
		return countHint;
	}
	public void setCountHint(int countHint) {
		this.countHint = countHint;
	}
	@Override
	public String toString() {
		return "GdFeedLink [href=" + href + ", countHint=" + countHint + "]";
	}
	
	
}
