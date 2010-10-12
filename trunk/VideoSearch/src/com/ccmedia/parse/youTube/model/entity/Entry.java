package com.ccmedia.parse.youTube.model.entity;

import java.util.Vector;

import com.ccmedia.parse.model.basicXMLEntry;

public class Entry extends basicXMLEntry{
	private Id id;
	private Published published;
	private AppControl appControl;
	private Vector<Category> category;
	private Title title;
	private Content content;
	private Vector<Link> link;
	private Author author;
	private GdComments gdComments;
	private MediaGroup mediaGroup;
	private YtNoembed ytNoembed;
	private GdRating gdRating;
	private YtRecorded ytRecorded;
	private YtStatistics ytStatistics;
	
	public Entry(){
		id = new Id();
		published = new Published();
		appControl = new AppControl();
		category = new Vector<Category>();
		title = new Title();
		content = new Content();
		link = new Vector<Link>();
		author = new Author();
		gdComments = new GdComments();
		mediaGroup = new MediaGroup();
		gdRating = new GdRating();
		ytRecorded = new YtRecorded();
		ytStatistics = new YtStatistics();
	}
	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Published getPublished() {
		return published;
	}
	public void setPublished(Published published) {
		this.published = published;
	}
	public AppControl getAppControl() {
		return appControl;
	}
	public void setAppControl(AppControl appControl) {
		this.appControl = appControl;
	}
	public Vector<Category> getCategory() {
		return category;
	}
	public void setCategory(Vector<Category> category) {
		this.category = category;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public Vector<Link> getLink() {
		return link;
	}
	public void setLink(Vector<Link> link) {
		this.link = link;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public GdComments getGdComments() {
		return gdComments;
	}
	public void setGdComments(GdComments gdComments) {
		this.gdComments = gdComments;
	}
	public MediaGroup getMediaGroup() {
		return mediaGroup;
	}
	public void setMediaGroup(MediaGroup mediaGroup) {
		this.mediaGroup = mediaGroup;
	}
	public YtNoembed getYtNoembed() {
		return ytNoembed;
	}
	public void setYtNoembed(YtNoembed ytNoembed) {
		this.ytNoembed = ytNoembed;
	}
	public GdRating getGdRating() {
		return gdRating;
	}
	public void setGdRating(GdRating gdRating) {
		this.gdRating = gdRating;
	}
	public YtRecorded getYtRecorded() {
		return ytRecorded;
	}
	public void setYtRecorded(YtRecorded ytRecorded) {
		this.ytRecorded = ytRecorded;
	}
	public YtStatistics getYtStatistics() {
		return ytStatistics;
	}
	public void setYtStatistics(YtStatistics ytStatistics) {
		this.ytStatistics = ytStatistics;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", published=" + published + ", appControl="
				+ appControl + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", link=" + link + ", author="
				+ author + ", gdComments=" + gdComments + ", mediaGroup="
				+ mediaGroup + ", ytNoembed=" + ytNoembed + ", gdRating="
				+ gdRating + ", ytRecorded=" + ytRecorded + ", ytStatistics="
				+ ytStatistics + "]";
	}
	
	
	
}
