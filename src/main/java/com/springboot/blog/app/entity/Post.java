package com.springboot.blog.app.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//for getter/setter/toString()
@Getter
@Setter
//for Creating All Argument Constructor
@AllArgsConstructor
@NoArgsConstructor
//for JPA Mapping
@Entity
//for creating table    with unique constraints into title field.
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")}
)

public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="content",nullable = false)
    private String content;


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments=new HashSet<>();

}
