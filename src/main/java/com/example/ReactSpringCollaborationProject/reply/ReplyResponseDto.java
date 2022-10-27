package com.example.ReactSpringCollaborationProject.reply;

import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import lombok.Getter;

@Getter
public class ReplyResponseDto {
    private Long id;
    private String reply;
    private String email;


    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.reply = reply.getReply();
        this.email = reply.getEmail();

    }
    public ReplyResponseDto(Long id, String email, String reply){
        this.id = id;
        this.reply = reply;
        this.email = email;
    }
}
