package com.example.ReactSpringCollaborationProject.comment.service;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.dto.CommentRequestDto;
import com.example.ReactSpringCollaborationProject.comment.dto.CommentResponseDto;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.comment.repository.CommentRepository;
import com.example.ReactSpringCollaborationProject.commentLike.CommentLike;
import com.example.ReactSpringCollaborationProject.commentLike.CommentLikeRepository;
import com.example.ReactSpringCollaborationProject.post.Post;

import com.example.ReactSpringCollaborationProject.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostRepository postRepository;

    //커멘트 생성
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        Comment comment = new Comment(requestDto, account, post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 커멘트 수정
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Long id, Account account) {

        var r = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("comment is not exist"));
        if (!account.getEmail().equals(r.getEmail())) {
            throw new RuntimeException("not matched email");
        }
        r.updateAll(requestDto);
        return new CommentResponseDto(r);
    }

    //커멘트 삭제
    public String deleteComment(Long Id, Account account) {
        var r = commentRepository.findById(Id).orElseThrow(
                () -> new RuntimeException("comment is not exist"));
        if (!account.getEmail().equals(r.getEmail())) {
            throw new RuntimeException("not matched email");}
        commentRepository.deleteById(Id);
        return "Delete success";
    }
    public List<CommentResponseDto> getAllMyComments(Account account) {
        List<Comment> comments = commentRepository.findAll();

        List<CommentResponseDto> responseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            if (commentLikeRepository.existsByCommentAndAccount(comment, account)) {
                comment.updateLikeState(true);
            }
            List<CommentLike> likes = comment.getLikes();
            comment.updateLikeCount(likes.size());
            responseDtos.add(new CommentResponseDto(comment));
        }   return responseDtos;
//        var commentLists = commentRepository.findAllByEmail(account.getEmail());
//        var commentResponseLists = new ArrayList<CommentResponseDto>();
//        for(Comment commentList: commentLists){
//            commentResponseLists.add(new CommentResponseDto(commentList));
//        }
//        return commentResponseLists;
    }
    //커멘트 읽기
    public ResponseEntity<?> getOneComment(Long id, Account account) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("글 없음"));

        if (commentLikeRepository.existsByCommentAndAccount(comment, account)) {
            comment.updateLikeState(true);
        }   return ResponseEntity.ok(comment);
    }
//        var r = commentRepository.findById(Id).orElseThrow(
//                () -> new RuntimeException("comment is not exist")
//        );
//        if (!r.getEmail().equals(account.getEmail())) {
//            throw new RuntimeException("email does not match");
//        }
//        return new CommentResponseDto(r);
//    }
}