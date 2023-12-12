package com.learn.blog.service.impl;

import com.learn.blog.exceptions.ResourceNotFoundException;
import com.learn.blog.model.Comment;
import com.learn.blog.model.Post;
import com.learn.blog.payloads.CommentDto;
import com.learn.blog.repo.CommentRepo;
import com.learn.blog.repo.PostRepo;
import com.learn.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId) {
        System.out.println(commentDto.getContent());
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        Comment addComment = this.commentRepo.save(comment);

        return this.modelMapper.map(addComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.commentRepo.deleteById(postId);

    }
}
