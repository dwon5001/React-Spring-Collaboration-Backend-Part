package com.example.ReactSpringCollaborationProject.comment.entity;

import com.example.ReactSpringCollaborationProject.Timestamped;
import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.dto.CommentRequestDto;
import com.example.ReactSpringCollaborationProject.commentLike.CommentLike;
import com.example.ReactSpringCollaborationProject.post.Post;
import com.example.ReactSpringCollaborationProject.postLike.PostLike;
import com.example.ReactSpringCollaborationProject.reply.Reply;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "post")//JoinColumn 하면 nullable = false 금지
    private Post post;

    @JsonIgnore //JPA 순환참조
    @ManyToOne
    @JoinColumn(name = "account_Id")//JoinColumn 하면 nullable = false 금지
    private Account account;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentLike> likes = new ArrayList<>();

    @Transient
    private int likeCount;

    @Transient
    private boolean likeState;

    @JsonIgnore //JPA 순환참조
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    public Comment(CommentRequestDto requestDto) {
        this.comments = requestDto.getComments();
    }

    public Comment(CommentRequestDto requestDto, Account account, Post post) {
        this.comments = requestDto.getComments();
        this.email = account.getEmail();
        this.post = post;
    }

    public void updateAll(CommentRequestDto requestDto) {
        this.comments = requestDto.getComments();
    }

    public void updateLikeCount(int size) {
        this.likeCount = size;
    }

    public void updateLikeState(boolean state) {
        this.likeState = state;
    }
}
