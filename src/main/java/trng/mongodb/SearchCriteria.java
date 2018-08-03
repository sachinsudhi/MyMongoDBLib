package trng.mongodb;

import java.util.Date;

public class SearchCriteria {
	private String blogName;
	private String author;
	private String tagName;
	private Date fromDate;
	private Date toDate;
	
	public SearchCriteria() {
		super();
	}
	

	public SearchCriteria(String blogName, String author, String tagName, Date fromDate, Date toDate) {
		super();
		this.blogName = blogName;
		this.author = author;
		this.tagName = tagName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}


	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
