package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Comment;
import com.example.imageGhost.Domain.Dto.CommentDto;
import com.example.imageGhost.Domain.Post;
import com.example.imageGhost.Repository.CommentRepository;
import com.example.imageGhost.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgoraController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public AgoraController(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    /*
        단일 게시물 조회
     */
    @GetMapping("/board/{id}")
    public Post getSinglePost(@PathVariable("id") Long postId){
        return postRepository.findById(postId).get();
    }

    /*
        댓글 작성
     */
    @PostMapping("/comment")
    public Comment saveComment(@RequestBody CommentDto commentDto){
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPostId(commentDto.getPostId());
        comment.setWriterPublicKey(commentDto.getWriterPublicKey());
        return commentRepository.save(comment);
    }

}
