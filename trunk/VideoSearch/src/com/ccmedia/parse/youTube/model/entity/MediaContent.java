package com.ccmedia.parse.youTube.model.entity;

public class MediaContent {
	private String type;
	private String url;
	private String medium;
	private String expression;
	private String duration;
	private String format;
	private String isDefault;
	
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MediaContent [type=" + type + ", url=" + url + ", medium="
				+ medium + ", expression=" + expression + ", duration="
				+ duration + ", format=" + format + ", isDefault=" + isDefault
				+ "]";
	}
}
