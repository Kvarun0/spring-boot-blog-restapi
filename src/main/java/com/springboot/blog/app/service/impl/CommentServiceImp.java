package com.springboot.blog.app.service.impl;

import com.springboot.blog.app.entity.Comment;
import com.springboot.blog.app.entity.Post;
import com.springboot.blog.app.exception.BlogAPIException;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.payload.CommentDto;
import com.springboot.blog.app.repository.CommentRepository;
import com.springboot.blog.app.repository.PostRepository;
import com.springboot.blog.app.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    private ModelMapper modelMapper;
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentServiceImp(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment=mapToEntity(commentDto);

        //find post with given id
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        //set post into comment
        comment.setPost(post);

        Comment savedComment=commentRepository.save(comment);

        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments=commentRepository.findByPostId(postId);

        //convert list of comment to list of commentDto
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //find post by id
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        //find comment by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment","id",commentId)
        );

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
        //  get the post
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        //  get the comment
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment=commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        commentRepository.delete(comment);

    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto=modelMapper.map(comment,CommentDto.class);
        return commentDto;
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
//        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;
//        Comment comment=new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//        return comment;
    }
}
