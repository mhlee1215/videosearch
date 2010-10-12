package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class YtStatistics extends basicXMLEntry{
	private int favoriteCount;
	private int viewCount;
	
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	@Override
	public String toString() {
		return "YtStatistics [favoriteCount=" + favoriteCount + ", viewCount="
				+ viewCount + "]";
	}
	
	
}
