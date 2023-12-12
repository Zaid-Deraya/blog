package com.learn.blog.payloads;

import com.learn.blog.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private Date date;
    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();


}
