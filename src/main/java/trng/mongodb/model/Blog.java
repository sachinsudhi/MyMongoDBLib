package trng.mongodb.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Blogs")
public class Blog {
	@Id
	String id;
	String title;
	String body;
	String author;
	Date date;
	List<BlogComment> comments;
	List<String> tags;
	
	
	public Blog() {
		super();
	}
	
	public Blog(List<BlogComment> comments) {
		super();
		this.comments = comments;
	}
	
	public Blog(String title, String body, String author,Date date) {
		super();
		this.title = title;
		this.body = body;
		this.author = author;
		this.date=date;
	}
	
	public Blog(String title, String body, String author, Date date, List<BlogComment> comments,
			List<String> tags) {
		super();
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
		this.comments = comments;
		this.tags = tags;
	}
	
	public Blog(String id,String title, String body, String author, Date date, List<BlogComment> comments,
			List<String> tags) {
		super();
		this.id=id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
		this.comments = comments;
		this.tags = tags;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<BlogComment> getComments() {
		return comments;
	}
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + id + ", title=" + title + ", body=" + body + ", author=" + author + ", date="
				+ date + ", comments=" + comments + ", tags=" + tags + "]";
	}
}
