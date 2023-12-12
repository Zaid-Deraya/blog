package com.learn.blog.repo;

import com.learn.blog.model.Category;
import com.learn.blog.model.Post;
import com.learn.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);
    List<Post> findByPostTitleContaining(String keyword);
}
