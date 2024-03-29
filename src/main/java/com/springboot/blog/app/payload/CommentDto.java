package com.springboot.blog.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    //not null and not empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //not null
    //at least 10 characters
    @NotEmpty
    @Size(min = 10,message = "Comment body must be minimum 10 characters")
    private String body;
}

