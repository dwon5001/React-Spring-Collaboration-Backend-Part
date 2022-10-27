package com.example.ReactSpringCollaborationProject.reply;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.dto.CommentRequestDto;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String reply;

    @Column(nullable = false)
    private String email;

    @JsonIgnore //JPA 순환참조
    @ManyToOne
    @JoinColumn(name = "account_Id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "comment")
    private Comment comment;

    public Reply(ReplyRequestDto requestDto) {
        this.reply = requestDto.getReply();
    }

    public Reply(ReplyRequestDto requestDto, Account account, Comment comment) {
        this.reply = requestDto.getReply();
        this.email = account.getEmail();
        this.comment = comment;
    }
    public ReplyResponseDto all(){
        return new ReplyResponseDto(this.id, this.email, this.reply);
    }

    public void update(ReplyRequestDto requestDto) {
        this.reply = requestDto.getReply();
    }
}
