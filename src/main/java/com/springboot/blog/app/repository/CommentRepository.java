package com.springboot.blog.app.repository;

import com.springboot.blog.app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //create a new sql query
    List<Comment> findByPostId(long postId);

}
