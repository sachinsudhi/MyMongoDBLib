package trng.mongodb.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;


import trng.mongodb.model.Blog;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String>, QueryDslPredicateExecutor<Blog>{

	public Optional<Blog> findById(String blogId);

	public void deleteById(String blogId);

	public List<Blog> findByAuthor(String author);
	
	@Query(value = "{'date':{ $lt: ?0, $gt: ?1}}")
	public List<Blog> findByDateBetween(Date from, Date to);

}
