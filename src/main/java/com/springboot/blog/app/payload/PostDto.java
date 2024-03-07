package com.springboot.blog.app.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private long id;

    //not null and have at least 2 character
    @NotEmpty
    @Size(min = 2,message = "Post title should have at least 2 characters")
    private String title;

    //not null and have at least 10 character
    @NotEmpty
    @Size(min = 10,message = "Post Description should have at least 10 characters")
    private String description;

    //not null
    @NotEmpty
    private String content;


    private Set<CommentDto> comments;
}
