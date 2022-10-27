package com.example.ReactSpringCollaborationProject.post;

import com.example.ReactSpringCollaborationProject.Timestamped;
import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.postLike.PostLike;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "post")
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userEmail;

//    private String title;

    private String content;
    private String imageUrl;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostLike> likes = new ArrayList<>();

    @Transient
    private int likeCount;

    @Transient
    private boolean likeState;

    @JsonIgnore //JPA 순환참조
    @ManyToOne
    @JoinColumn(name = "account_Id")
    private Account account;

    @JsonIgnore //JPA 순환참조
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public Post (PostResponseDto responseDto) {
        this.userEmail = responseDto.getUserEmail();
//        this.title = responseDto.getTitle();
        this.content = responseDto.getContent();
        this.imageUrl = responseDto.getImageUrl();
    }

//    post 사진있을 때
    public Post (String content, Account account, String image) {
        this.userEmail = account.getEmail();
//        this.title = requestDto.getTitle();
        this.content = content;
        this.imageUrl = image;
    }
//    post 사진없이 만들때
    public Post (String content, Account account) {
        this.userEmail = account.getEmail();
//        this.title = requestDto.getTitle();
        this.content = content;
        this.imageUrl = "";
    }

    public void updateComments(List<Comment> comments){
        this.comments = comments;
    }

    public void updateLikeCount(int size) {
        this.likeCount = size;
    }

    public void updateLikeState(boolean state) {
        this.likeState = state;
    }

    public void updatePost(String content) {
        this.content = content;
    }

}