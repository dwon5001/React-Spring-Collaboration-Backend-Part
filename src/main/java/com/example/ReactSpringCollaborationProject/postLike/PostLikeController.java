package com.example.ReactSpringCollaborationProject.postLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.global.dto.ResponseDto;
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
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/api/like")
    public ResponseDto<?> addOrDelete(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @RequestBody PostLikeRequestDto requestDto) throws SQLException {
        Account account = userDetails.getAccount();
        return ResponseDto.success(postLikeService.addOrDelete(account, requestDto));
    }
}
