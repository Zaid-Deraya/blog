package com.learn.blog.controller;

import com.learn.blog.payloads.CommentDto;
import com.learn.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("/addComment/post/{postId}")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

        CommentDto addCommentDto = this.commentService.addComment(commentDto, postId);
        return ResponseEntity.ok(addCommentDto);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
    }


}
