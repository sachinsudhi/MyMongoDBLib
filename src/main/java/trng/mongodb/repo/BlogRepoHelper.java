package trng.mongodb.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import trng.mongodb.model.Blog;
import trng.mongodb.model.BlogComment;

public class BlogRepoHelper {
	
	public static List<Document> prepareBlogDocuments(List<Blog> blogs) {
		return blogs.stream().map(blog -> prepareBlogDocument(blog)).collect(Collectors.toList());
	}
	
	public static Document prepareBlogDocument(Blog blog) {
		Document document = new Document().append("title", blog.getTitle()).append("author", blog.getAuthor())
				.append("date", blog.getDate()).append("body", blog.getBody());
		
		if (blog.getComments() != null) {
			document.append("comments", prepareBlogCommentDocuments(blog.getComments()));
		}
		
		if (blog.getTags() != null) {
			document.append("tags", blog.getTags());
		}
		
		return document;
	}

	private static List<Document> prepareBlogCommentDocuments(List<BlogComment> comments) {
		if (comments == null) { return null; }
		
		return comments.stream().map(document -> prepareBlogCommentDocument(document)).collect(Collectors.toList());
	}

	private static Document prepareBlogCommentDocument(BlogComment comment) {
		return new Document().append("name", comment.getName()).append("email", comment.getEmail()).append("comment",
				comment.getComment());
	}

	public static List<Blog> prepareBlogs(FindIterable<Document> blogCollections) {
		List<Blog> blogs = new ArrayList<>();
		MongoCursor<Document> itr = blogCollections.iterator();
		
		try {
			while(itr.hasNext()) {
				blogs.add(prepareBlog(itr.next()));
			}
		} finally {
			itr.close();
		}
		
		return blogs;
	}

	public static List<Blog> prepareBlogs(List<Document> blogCollections) {
		return blogCollections.stream().map(blog-> prepareBlog(blog)).collect(Collectors.toList());
	}
	
	public static Blog prepareBlog(Document document) {
		Blog blog = new Blog(document.getObjectId("_id").toString(), document.getString("title"), document.getString("body"),
				document.getString("author"), document.getDate("date"), null, null);
		
		return blog;
	}

}
