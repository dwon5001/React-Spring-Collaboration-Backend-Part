package com.example.ReactSpringCollaborationProject.postLike;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndAccount(Post post, Account account);

    boolean existsByPostAndAccount(Post post, Account account);
}
