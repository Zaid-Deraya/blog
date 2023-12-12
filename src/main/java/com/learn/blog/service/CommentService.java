package com.learn.blog.service;

import com.learn.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer postId);
}
