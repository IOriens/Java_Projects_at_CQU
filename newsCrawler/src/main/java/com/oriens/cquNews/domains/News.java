package com.oriens.cquNews.domains;

public class News {
	private String id;
	private String title;
	private String description;	
	private String content;
	private String thumb;
	
	public News(){
		super();
	}
	
	public News(String id,String title,String description,String content,String thumb) {
		super();
		this.content=content;
		this.description=description;
		this.id=id;
		this.thumb=thumb;
		this.title=title;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
}
