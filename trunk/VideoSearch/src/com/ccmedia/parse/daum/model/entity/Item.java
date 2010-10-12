package com.ccmedia.parse.daum.model.entity;

import java.util.Vector;

import com.ccmedia.parse.model.basicXMLEntry;

public class Item extends basicXMLEntry{
	private Title title;
	private Description description;
	private Tag tag;
	private Link link;
	private Cpname cpname;
	private Author author;
	private Player_url player_url;
	private Playcnt playcnt;
	private Score score;
	private Recommend recommend;
	private PubDate pubDate;
	private Playtime playtime;
	private Bitrate bitrate;
	private Vector<Time> time;
	private Thumbnail thumbnail;
	private Vector<Thumb> thumb;
	
	public Item(){
		title = new Title();
		description = new Description();
		tag = new Tag();
		link = new Link();
		cpname = new Cpname();
		author = new Author();
		player_url = new Player_url();
		playcnt = new Playcnt();
		score = new Score();
		recommend = new Recommend();
		pubDate = new PubDate();
		playtime = new Playtime();
		bitrate = new Bitrate();
		time = new Vector<Time>();
		thumbnail = new Thumbnail();
		thumb = new Vector<Thumb>();
	}
	
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	public Cpname getCpname() {
		return cpname;
	}
	public void setCpname(Cpname cpname) {
		this.cpname = cpname;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Player_url getPlayer_url() {
		return player_url;
	}
	public void setPlayer_url(Player_url player_url) {
		this.player_url = player_url;
	}
	public Playcnt getPlaycnt() {
		return playcnt;
	}
	public void setPlaycnt(Playcnt playcnt) {
		this.playcnt = playcnt;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public Recommend getRecommend() {
		return recommend;
	}
	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
	public PubDate getPubDate() {
		return pubDate;
	}
	public void setPubDate(PubDate pubDate) {
		this.pubDate = pubDate;
	}
	public Playtime getPlaytime() {
		return playtime;
	}
	public void setPlaytime(Playtime playtime) {
		this.playtime = playtime;
	}
	public Bitrate getBitrate() {
		return bitrate;
	}
	public void setBitrate(Bitrate bitrate) {
		this.bitrate = bitrate;
	}
	public Vector<Time> getTime() {
		return time;
	}
	public void setTime(Vector<Time> time) {
		this.time = time;
	}
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Vector<Thumb> getThumb() {
		return thumb;
	}
	public void setThumb(Vector<Thumb> thumb) {
		this.thumb = thumb;
	}
	@Override
	public String toString() {
		return "Item [title=" + title + ", description=" + description
				+ ", tag=" + tag + ", link=" + link + ", cpname=" + cpname
				+ ", author=" + author + ", player_url=" + player_url
				+ ", playcnt=" + playcnt + ", score=" + score + ", recommend="
				+ recommend + ", pubDate=" + pubDate + ", playtime=" + playtime
				+ ", bitrate=" + bitrate + ", time=" + time + ", thumbnail="
				+ thumbnail + ", thumb=" + thumb + "]";
	}
}
