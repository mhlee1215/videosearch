package com.ccmedia.parse.model;

import java.util.Vector;

public class SearchResultEntity {
	private String prividerName;
	private String title;
	private int startIndex;
	private int totalResults;
	private int itemsPerPage;
	private Vector<VideoEntity> videoEntities;
	
	public SearchResultEntity(){
		videoEntities = new Vector<VideoEntity>();
	}

	public String getPrividerName() {
		return prividerName;
	}

	public void setPrividerName(String providerName) {
		this.prividerName = providerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Vector<VideoEntity> getVideoEntities() {
		return videoEntities;
	}

	public void setVideoEntities(Vector<VideoEntity> videoEntities) {
		this.videoEntities = videoEntities;
	}

	@Override
	public String toString() {
		return "SearchResultEntity [prividerName=" + prividerName + ", title="
				+ title + ", startIndex=" + startIndex + ", totalResults="
				+ totalResults + ", itemsPerPage=" + itemsPerPage
				+ ", videoEntities=" + videoEntities + "]";
	}
}
