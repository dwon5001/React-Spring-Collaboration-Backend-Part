package com.example.ReactSpringCollaborationProject.comment.dto;

import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.reply.Reply;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comments;
    private String email;

    private int likeCount;
    private boolean likeState;
    private List<Reply> replies;
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comments = comment.getComments();
        this.email = comment.getEmail();
        this.likeCount = comment.getLikeCount();
        this.likeState = comment.isLikeState();
        this.replies = comment.getReplies();
    }


}