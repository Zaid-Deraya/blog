package com.learn.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageNUmber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
