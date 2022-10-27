package com.example.ReactSpringCollaborationProject.commentLike;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentLikeRequestDto {

    private Long commentId;

    private boolean likeState;
}
