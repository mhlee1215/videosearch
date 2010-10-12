package com.ccmedia.parse.youTube.model.entity;

import java.util.Vector;

import com.ccmedia.parse.model.basicXMLEntry;

public class MediaGroup extends basicXMLEntry{
	private MediaCategory mediaCategory;
	private Vector<MediaContent> mediaContent;
	private MediaDescription mediaDescription;
	private MediaKeywords mediaKeywords;
	private MediaPlayer mediaPlayer;
	private MediaRestriction mediaRestriction;
	private Vector<MediaThumbnail> mediaThumbnail;
	private Title title;
	private YtDuration ytDuration;
	
	public MediaGroup(){
		mediaCategory = new MediaCategory();
		mediaContent = new Vector<MediaContent>();
		mediaDescription = new MediaDescription();
		mediaKeywords = new MediaKeywords();
		mediaPlayer = new MediaPlayer();
		mediaRestriction = new MediaRestriction();
		mediaThumbnail = new Vector<MediaThumbnail>();
		title = new Title();
		ytDuration = new YtDuration();
	}
	
	public Vector<MediaContent> getMediaContent() {
		return mediaContent;
	}
	public void setMediaContent(Vector<MediaContent> mediaContent) {
		this.mediaContent = mediaContent;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public MediaCategory getMediaCategory() {
		return mediaCategory;
	}
	public void setMediaCategory(MediaCategory mediaCategory) {
		this.mediaCategory = mediaCategory;
	}
	public MediaDescription getMediaDescription() {
		return mediaDescription;
	}
	public void setMediaDescription(MediaDescription mediaDescription) {
		this.mediaDescription = mediaDescription;
	}
	public MediaKeywords getMediaKeywords() {
		return mediaKeywords;
	}
	public void setMediaKeywords(MediaKeywords mediaKeywords) {
		this.mediaKeywords = mediaKeywords;
	}
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
	public MediaRestriction getMediaRestriction() {
		return mediaRestriction;
	}
	public void setMediaRestriction(MediaRestriction mediaRestriction) {
		this.mediaRestriction = mediaRestriction;
	}
	public Vector<MediaThumbnail> getMediaThumbnail() {
		return mediaThumbnail;
	}
	public void setMediaThumbnail(Vector<MediaThumbnail> mediaThumbnail) {
		this.mediaThumbnail = mediaThumbnail;
	}
	public YtDuration getYtDuration() {
		return ytDuration;
	}
	public void setYtDuration(YtDuration ytDuration) {
		this.ytDuration = ytDuration;
	}

	@Override
	public String toString() {
		return "MediaGroup [mediaCategory=" + mediaCategory + ", mediaContent="
				+ mediaContent + ", mediaDescription=" + mediaDescription
				+ ", mediaKeywords=" + mediaKeywords + ", mediaPlayer="
				+ mediaPlayer + ", mediaRestriction=" + mediaRestriction
				+ ", mediaThumbnail=" + mediaThumbnail + ", title=" + title
				+ ", ytDuration=" + ytDuration + "]";
	}
	
	
}
