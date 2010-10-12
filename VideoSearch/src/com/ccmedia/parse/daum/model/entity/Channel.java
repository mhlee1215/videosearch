package com.ccmedia.parse.daum.model.entity;

import java.util.Vector;

import com.ccmedia.parse.model.basicXMLEntry;

public class Channel extends basicXMLEntry{
	private Title title;
	private Link link;
	private Description description;
	private LastBuildDate lastBuildDate;
	private Generator generator;
	private TotalCount totalCount;
	private Result result;
	private Sort sort;
	private Q q;
	private Tagsearch tagsearch;
	private Pageno pageno;
	private Vector<Item> item;
	
	public Channel(){
		title = new Title();
		link = new Link();
		description = new Description();
		lastBuildDate = new LastBuildDate();
		generator = new Generator();
		totalCount = new TotalCount();
		result = new Result();
		sort = new Sort();
		q = new Q();
		tagsearch = new Tagsearch();
		pageno = new Pageno();
		item = new Vector<Item>();
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public LastBuildDate getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(LastBuildDate lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public Generator getGenerator() {
		return generator;
	}
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
	public TotalCount getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(TotalCount totalCount) {
		this.totalCount = totalCount;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public Q getQ() {
		return q;
	}
	public void setQ(Q q) {
		this.q = q;
	}
	public Tagsearch getTagsearch() {
		return tagsearch;
	}
	public void setTagsearch(Tagsearch tagsearch) {
		this.tagsearch = tagsearch;
	}
	public Pageno getPageno() {
		return pageno;
	}
	public void setPageno(Pageno pageno) {
		this.pageno = pageno;
	}
	public Vector<Item> getItem() {
		return item;
	}
	public void setItem(Vector<Item> item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Channel [title=" + title + ", link=" + link + ", description="
				+ description + ", lastBuildDate=" + lastBuildDate
				+ ", generator=" + generator + ", totalCount=" + totalCount
				+ ", result=" + result + ", sort=" + sort + ", q=" + q
				+ ", tagsearch=" + tagsearch + ", pageno=" + pageno + ", item="
				+ item + "]";
	}
}
