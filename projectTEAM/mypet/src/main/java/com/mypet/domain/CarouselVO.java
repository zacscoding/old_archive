package com.mypet.domain;

public class CarouselVO {
	private String image;
	private String content;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CareselVO [image=" + image + ", content=" + content + "]";
	}
}
