package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    List<Comment> findByPostId(Long postId);
}
