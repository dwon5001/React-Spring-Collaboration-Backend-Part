package com.example.ReactSpringCollaborationProject.commentLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public boolean addOrDelete(Account account, CommentLikeRequestDto requestDto) {
        Comment comment = commentRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new RuntimeException("글 없음"));

        Optional<CommentLike> foundLike = commentLikeRepository.findByCommentAndAccount(comment, account);
        if (foundLike.isPresent()) {
            commentLikeRepository.delete(foundLike.get());
            return false;
        } else {
            CommentLike commentLike = new CommentLike(account, comment);
            commentLikeRepository.save(commentLike);
            return true;
        }
    }
}
