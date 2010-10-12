package com.ccmedia.parse.youTube.model.entity;

import java.util.Vector;

import com.ccmedia.parse.model.basicXMLEntry;

public class Feed extends basicXMLEntry{
	private Id id;
	private Updated updated;
	private Category category;
	private Title title;
	private Logo logo;
	private Vector<Link> link;
	private Author author;
	private Generator generator;
	private TotalResult totalResult;
	private StartIndex startIndex;
	private ItemsPerPage itemsPerPage;
	private Vector<Entry> entry;
	
	public Feed(){
		id = new Id();
		updated = new Updated();
		category = new Category();
		title = new Title();
		logo = new Logo();
		link = new Vector<Link>();
		author = new Author();
		generator = new Generator();
		totalResult = new TotalResult();
		startIndex = new StartIndex();
		itemsPerPage = new ItemsPerPage();
		entry = new Vector<Entry>();
	}
	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Updated getUpdated() {
		return updated;
	}
	public void setUpdated(Updated updated) {
		this.updated = updated;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Logo getLogo() {
		return logo;
	}
	public void setLogo(Logo logo) {
		this.logo = logo;
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
	public Generator getGenerator() {
		return generator;
	}
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
	public Vector<Entry> getEntry() {
		return entry;
	}
	public void setEntry(Vector<Entry> entry) {
		this.entry = entry;
	}

	public TotalResult getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(TotalResult totalResult) {
		this.totalResult = totalResult;
	}

	public StartIndex getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(StartIndex startIndex) {
		this.startIndex = startIndex;
	}

	public ItemsPerPage getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(ItemsPerPage itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	@Override
	public String toString() {
		return "Feed [id=" + id + ", updated=" + updated + ", category="
				+ category + ", title=" + title + ", logo=" + logo + ", link="
				+ link + ", author=" + author + ", generator=" + generator
				+ ", totalResult=" + totalResult + ", startIndex=" + startIndex
				+ ", itemsPerPage=" + itemsPerPage + ", entry=" + entry + "]";
	}
	
	
}
