package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class GdRating extends basicXMLEntry{
	private float average;
	private int max;
	private int min;
	private int numRaters;
	private String rel;
	
	public float getAverage() {
		return average;
	}
	public void setAverage(float average) {
		this.average = average;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getNumRaters() {
		return numRaters;
	}
	public void setNumRaters(int numRaters) {
		this.numRaters = numRaters;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	
}
