package com.example.ReactSpringCollaborationProject.postLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.post.Post;
import com.example.ReactSpringCollaborationProject.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public boolean addOrDelete(Account account, PostLikeRequestDto requestDto) {
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("글 없음"));

        Optional<PostLike> foundLike = postLikeRepository.findByPostAndAccount(post, account);
        if (foundLike.isPresent()) {

            postLikeRepository.delete(foundLike.get());
            return false;
        } else {
            PostLike postLike = new PostLike(account, post);
            postLikeRepository.save(postLike);
            return true;
        }
    }
}