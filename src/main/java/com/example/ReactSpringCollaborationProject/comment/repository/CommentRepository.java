package com.example.ReactSpringCollaborationProject.comment.repository;

import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
   List<Comment> findAllByPostId(Long postId);
   Comment findById(String Id);
   List<Comment> findAllByEmail(String Email);
}