package com.example.ReactSpringCollaborationProject.commentLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RequiredArgsConstructor
@RestController
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/api/commentlike")
    public ResponseEntity<?> addOrDelete(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @RequestBody CommentLikeRequestDto requestDto) {
        Account account = userDetails.getAccount();
        return ResponseEntity.ok(commentLikeService.addOrDelete(account, requestDto));
    }
}
