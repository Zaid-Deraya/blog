package com.learn.blog.service;

import com.learn.blog.model.Post;
import com.learn.blog.payloads.PostDto;
import com.learn.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto post,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto post, Integer id);

    void deletePost(Integer id);

    PostDto getPostById(Integer id);

    PostResponse getAllPost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
