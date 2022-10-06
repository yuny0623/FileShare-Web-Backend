package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);

    Optional<Post> findById(Long postId);
}
