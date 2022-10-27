package com.example.ReactSpringCollaborationProject.post;


import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    Optional<Post> findById(String Id);

    List<Post> findAllByUserEmail(String UserEmail);
}
