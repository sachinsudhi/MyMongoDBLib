package trng.mongodb;

import java.util.Arrays;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import trng.mongodb.model.Blog;
import trng.mongodb.model.BlogComment;
import trng.mongodb.repo.BlogRepository;

@SpringBootApplication
public class MongoBlogOperationsApp {
    @Autowired
	private BlogRepository blogRepository;

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(MongoBlogOperationsApp.class, args);
		MongoBlogOperationsApp mongoBlogOprApp = new MongoBlogOperationsApp();

		System.out.println("Choose the operation");
		boolean flag = true;

		do {
			System.out.println("1 Add \n 2 DELETE \n 3 UPDATE \n 4 SELECT \n 5. Add Blog with Details "
					+ "\n 6. List Blogs \n 7. Select Address \n 8. Serach on Criteria \n 9. Select Student by Address "
					+ "\n 10. Select Blog with Minimal Info");
			BlogOperation opVal = BlogOperation.getValue(scanner.nextInt());

			switch (opVal) {
			case ADD_BLOG:
				mongoBlogOprApp.addBlog();
				break;

			case DELETE_BLOG:
				mongoBlogOprApp.deleteBlog();
				break;

			case UPDATE_BLOG:
				mongoBlogOprApp.updateBlog();
				break;

			case SELECT_BLOG:
				mongoBlogOprApp.selectBlog();
				break;

			case ADD_BLOG_WITH_DETAILS:
				mongoBlogOprApp.addBlogWithDeails();
				break;

			case LIST_BLOGS:
				mongoBlogOprApp.listBlogs();
				break;

			case SEARCH_ON_CRITERIA:
				//mongoBlogOprApp.searchOnCriteria();
				break;

			case SELECT_BLOG_WITH_MINIMAL_INFO:
				mongoBlogOprApp.searchWithMinimalInfo();
				break;

			default:
				flag = false;
			}
		} while (flag);

		scanner.close();
	}

	private void searchWithMinimalInfo() {
		// SearchCriteria sc = new SearchCriteria();
		// sc.setBlogName("Java8 Made Easy");
		Date from = new Date(2000, 01, 01);
		Date to = new Date();

		List<Blog> blogs = blogRepository.findByDateBetween(from, to);
		for (Blog blog : blogs) {
			System.out.println(blog);
		}

	}

	private void selectBlogByAuthor() {
		System.out.println("Enter Author: ");
		String author = scanner.nextLine();
		List<Blog> blogs = blogRepository.findByAuthor(author);
		System.out.println(blogs);
	}

	/*private void searchOnCriteria() {
		String blogName = "Java8 Made Easy";
		String author = "Ravi Kumar";
		String tagName = "Java8";

		SearchCriteria sc = new SearchCriteria();
		sc.setBlogName(blogName);
		sc.setAuthor(author);
		sc.setTagName(tagName);

		List<Blog> blogs = blogRepository.getBlogDetailsWithCriteria(sc);
		System.out
				.println("Blog details for [name: " + blogName + ", author: " + author + " + tagName " + tagName + "]");
		for (Blog blog : blogs) {
			System.out.println(blog);
		}
	}*/

	private void listBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			System.out.println(blog);
		}
	}

	private void selectBlog() {
		System.out.println("Enter blog ID: ");
		String blogId = scanner.next();
		Optional<Blog> blog = blogRepository.findById(blogId);
		if (blog == null) {
			System.out.println("No Blog found with given Id");
		} else {
			System.out.println(blog);
		}
	}

	private void deleteBlog() {
		System.out.println("Enter blog ID: ");
		String blogId = scanner.next();
		blogRepository.deleteById(blogId);
		/*
		 * if (! deleted) { System.out.println("Deleted Successfully"); } else {
		 * System.out.println("No Blog found with given Id"); }
		 */
	}

	private void addBlog() {
		Blog blog = new Blog("Java8 Made Easy", "Java8 course in 15 days", "Ravi Kumar", new Date());

		blogRepository.insert(blog);
	}

	private void addBlogWithDeails() {
		List<BlogComment> comments = Arrays
				.asList(new BlogComment("Mohan", "mohan@yahoo.com", "Yes, this course is useful"));
		List<String> tags = Arrays.asList("Java8", "Java course");
		Blog blog = new Blog("Java8 Made Easy", "Java8 course in 15 days", "Ravi Kumar", new Date(), comments, tags);

		blogRepository.insert(blog);
	}

	private void updateBlog() {
		System.out.println("Enter blog ID: ");
		String id = scanner.next();

		List<BlogComment> comments = Arrays
				.asList(new BlogComment("Nyshu", "nyshu@yahoo.com", "Yes, this course is best"));
		List<String> tags = Arrays.asList("Java8", "Java course");
		Blog blog = new Blog(id, "Java8 is Easy", "Java8 course in few days", "Nyshu", new Date(), comments, tags);

		blog.setId(id);
		blogRepository.save(blog);
	}

	enum BlogOperation {
		ADD_BLOG(1), DELETE_BLOG(2), UPDATE_BLOG(3), SELECT_BLOG(4), ADD_BLOG_WITH_DETAILS(5), LIST_BLOGS(
				6), SELECT_COMMENTS(
						7), SEARCH_ON_CRITERIA(8), SELECT_BLOG_BY_AUTHOR(9), SELECT_BLOG_WITH_MINIMAL_INFO(10);

		int operationVal;

		BlogOperation(int opVal) {
			operationVal = opVal;
		}

		static BlogOperation getValue(int opVal) {
			for (BlogOperation blogOperation : BlogOperation.values()) {
				if (opVal == blogOperation.operationVal) {
					return blogOperation;
				}
			}

			return null;
		}
	}
}
