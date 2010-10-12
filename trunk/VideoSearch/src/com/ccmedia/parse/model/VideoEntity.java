package com.ccmedia.parse.model;

public class VideoEntity {
	private String label;
	private String category;
	private int duration;
	private String title;
	private String author;
	private String description;
	private String playerUrl;
	private String streamingUrl;
	private String thumbnailUrl;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlayerUrl() {
		return playerUrl;
	}
	public void setPlayerUrl(String playerUrl) {
		this.playerUrl = playerUrl;
	}
	public String getStreamingUrl() {
		return streamingUrl;
	}
	public void setStreamingUrl(String streamingUrl) {
		this.streamingUrl = streamingUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	@Override
	public String toString() {
		return "VideoEntity [label=" + label + ", category=" + category
				+ ", duration=" + duration + ", title=" + title + ", author="
				+ author + ", description=" + description + ", playerUrl="
				+ playerUrl + ", streamingUrl=" + streamingUrl
				+ ", thumbnailUrl=" + thumbnailUrl + "]";
	}
}
