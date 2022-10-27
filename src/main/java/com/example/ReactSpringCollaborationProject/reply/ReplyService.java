package com.example.ReactSpringCollaborationProject.reply;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.repository.CommentRepository;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final CommentRepository commentRepository;

    //커멘트 생성
    public ReplyResponseDto createReply(Long id, ReplyRequestDto requestDto, Account account) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("글 없다"));
        Reply reply = new Reply(requestDto, account, comment);
        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }

    // 커멘트 수정
    public ReplyResponseDto updateReply(ReplyRequestDto requestDto, Long id, Account account) {

        var r = replyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("comment is not exist"));
        if (!account.getEmail().equals(r.getEmail())) {
            throw new RuntimeException("not matched email");
        }
        r.update(requestDto);
        return new ReplyResponseDto(r);
    }

    //커멘트 삭제
    public String deleteReply(Long Id, Account account) {
        var r = replyRepository.findById(Id).orElseThrow(
                () -> new RuntimeException("comment is not exist"));
        if (!account.getEmail().equals(r.getEmail())) {
            throw new RuntimeException("not matched email");
        }
        replyRepository.deleteById(Id);
        return "Delete success";
    }

    public List<ReplyResponseDto> getAllMyReplies() {
        List<ReplyResponseDto> responseDtos = replyRepository.findAll()
                .stream().map(reply -> reply.all()).collect(Collectors.toList());

           return responseDtos;
    }

    //커멘트 읽기
    public ReplyResponseDto getOneReply(Long id, Account account) {

        var r = replyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("comment is not exist")
        );
        if (!r.getEmail().equals(account.getEmail())) {
            throw new RuntimeException("email does not match");
        }
        return new ReplyResponseDto(r);
    }
}
