package com.example.ReactSpringCollaborationProject.commentLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentAndAccount(Comment comment, Account account);

    boolean existsByCommentAndAccount(Comment comment, Account account);
}
