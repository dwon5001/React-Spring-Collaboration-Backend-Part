package com.example.ReactSpringCollaborationProject.postLike;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLikeRequestDto {

    private Long postId;

    private boolean likeState;
}
