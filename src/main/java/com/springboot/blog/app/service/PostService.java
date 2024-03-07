package com.springboot.blog.app.service;

import com.springboot.blog.app.payload.PostDto;
import com.springboot.blog.app.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto,Long id);

    void deletePostById(long id);
}
