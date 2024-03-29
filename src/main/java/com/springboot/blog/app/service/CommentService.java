package com.springboot.blog.app.service;

import com.springboot.blog.app.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateCommentById(long postId,long commentId,CommentDto commentDto);

    void deleteCommentById(long postId,long commentId);
}