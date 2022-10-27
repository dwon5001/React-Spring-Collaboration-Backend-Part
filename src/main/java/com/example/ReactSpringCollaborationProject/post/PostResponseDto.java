package com.example.ReactSpringCollaborationProject.post;

import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String userEmail;
//    private String title;
    private String content;
    private String imageUrl;
    private int likeCount;
    private boolean likeState;
    private List<Comment> comments;

    public PostResponseDto (Post post){
        this.id = post.getId();
        this.userEmail = post.getUserEmail();
//        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.likeCount = post.getLikeCount();
        this.likeState = post.isLikeState();
        this.comments = post.getComments();
    }

}
