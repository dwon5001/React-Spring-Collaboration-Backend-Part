package com.example.ReactSpringCollaborationProject.reply;


import com.example.ReactSpringCollaborationProject.global.dto.ResponseDto;
import com.example.ReactSpringCollaborationProject.security.user.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reply")
@RequiredArgsConstructor
@RestController
public class ReplyController {

    public final ReplyService replyService;

//    @Autowired
//    CommentController(CommentService commentService) {
//        this.replyService = commentService;
//    }

    // 댓글쓰기 api
    @PostMapping("/{commentId}")
    public ResponseDto<?> createReply(@PathVariable Long commentId,
                                      @RequestBody ReplyRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(replyService.createReply(commentId, requestDto, userDetails.getAccount()));
    }

    // 댓글수정 api
    @PutMapping("/{id}")
    public ResponseDto<?> updateReply(@RequestBody ReplyRequestDto requestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(replyService.updateReply(requestDto, id, userDetails.getAccount()));
    }

    // 댓글삭제 api
    @DeleteMapping("/{id}")
    public ResponseDto<?> deleteReply(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(replyService.deleteReply(id,userDetails.getAccount()));
    }

    //댓글 1개 읽기 api
    @GetMapping("/{id}")
    public ResponseDto<?> getOneReply(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(replyService.getOneReply(id,userDetails.getAccount()));
    }

    //내 모든 comments 보여주기
    @GetMapping("/my")
    public ResponseDto<?> getAllMyReplies() {
        return ResponseDto.success(replyService.getAllMyReplies());
    }
}
