package com.springboot.blog.app.service.impl;

import com.springboot.blog.app.entity.Post;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.payload.PostDto;
import com.springboot.blog.app.payload.PostResponse;
import com.springboot.blog.app.repository.PostRepository;
import com.springboot.blog.app.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private ModelMapper modelMapper;
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert dto to entity
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        Post post = mapToEntity(postDto);

        //save Post To Database
        Post newPost = postRepository.save(post);

        //convert entity to dto
//        PostDto postResponse=new PostDto();
//        postResponse.setId(post.getId());
//        postResponse.setTitle(post.getTitle());
//        postResponse.setDescription(post.getContent());
//        postResponse.setContent(post.getContent());

        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);

        /*
        public static PageRequest of(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
                        return of(pageNumber, pageSize, Sort.by(direction, properties));
        }
         */

        Page<Post> posts = postRepository.findAll(pageRequest);

        List<Post> listOfProduct = posts.getContent();

        List<PostDto> content = listOfProduct.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElement(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        //update post and add to db
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        //return post

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


    //convert entity to dto
    private PostDto mapToDto(Post post) {
        PostDto postDto=modelMapper.map(post,PostDto.class);
        return postDto;
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getContent());
//        postDto.setContent(post.getContent());
//        return postDto;
    }

    //convert dto to entity method
    private Post mapToEntity(PostDto postDto) {
        Post post=modelMapper.map(postDto,Post.class);
        return post;
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        return post;
    }

}
