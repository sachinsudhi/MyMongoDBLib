package trng.mongodb.controller;

import com.querydsl.core.types.dsl.BooleanExpression;

import trng.mongodb.model.Blog;
import trng.mongodb.repo.BlogRepoHelper;
import trng.mongodb.repo.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    private BlogRepository blogrepository;

@Autowired
org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;
    public BlogController(BlogRepository blogrepository) {
        this.blogrepository = blogrepository;
    }

    @GetMapping("/all")
    public List<Blog> getAll(){
        List<Blog> blogs = this.blogrepository.findAll();

        return blogs;
    }
    
    @GetMapping("/count")
    public long getCount(){
        long blogCount = this.blogrepository.count();

        return blogCount;
    }

    @PutMapping
    public void insert(@RequestBody Blog blog){
        this.blogrepository.insert(blog);
    }
    
    @PutMapping
    public void addMany(@RequestBody List<Blog> blogs){
    	for(Blog blog:blogs)
        this.blogrepository.insert(blog);
    }
    
    @PutMapping("/addcomment")
    public void addComment(@RequestBody Blog blog){
        this.blogrepository.save(blog);
    }

    @PostMapping
    public void update(@RequestBody Blog blog){
        this.blogrepository.save(blog);
    }

    @GetMapping("/{blogId}")
    public Optional<Blog> getById(@PathVariable("blogId") String blogId){
        Optional<Blog> blog = this.blogrepository.findById(blogId);

        return blog;
    }
    
    @GetMapping("/{author}")
    public List<Blog> getByAuthor(@PathVariable("author") String author){
        List<Blog> blog = this.blogrepository.findByAuthor(author);

        return blog;
    }
    
    @GetMapping("/{fromDate}/{toDate}")
    public List<Blog> getByDates(@PathVariable("fromDate")Date from,@PathVariable("toDate")Date to){
        List<Blog> blogs = this.blogrepository.findByDateBetween(from, to);

        return blogs;
    }
}
