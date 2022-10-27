package com.example.ReactSpringCollaborationProject.reply;

import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
