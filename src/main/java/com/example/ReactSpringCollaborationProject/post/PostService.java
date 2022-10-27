package com.example.ReactSpringCollaborationProject.post;

import com.example.ReactSpringCollaborationProject.account.entity.Account;
import com.example.ReactSpringCollaborationProject.aws_s3.S3Uploader;
import com.example.ReactSpringCollaborationProject.comment.repository.CommentRepository;
import com.example.ReactSpringCollaborationProject.comment.entity.Comment;
import com.example.ReactSpringCollaborationProject.exception.CustomException;
import com.example.ReactSpringCollaborationProject.exception.ErrorCode;
import com.example.ReactSpringCollaborationProject.postLike.PostLike;
import com.example.ReactSpringCollaborationProject.postLike.PostLikeRepository;
import com.example.ReactSpringCollaborationProject.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public List<PostResponseDto> getAllPosts(UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();

        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : posts) {
            if (postLikeRepository.existsByPostAndAccount(post, account)) {
                post.updateLikeState(true);
                // likerepository 에서 지금 로그인한 유저가 좋아요한 글이면 boolean true
            }

            List<Comment> comments = commentRepository.findAllByPostId(post.getId());
            post.updateComments(comments);

            List<PostLike> likes = post.getLikes();
            post.updateLikeCount(likes.size());

            responseDtos.add(new PostResponseDto(post));
        }
        return responseDtos;

//        List<PostResponseDto> responseDtos = postRepository.findAll()
//                .stream().map(post -> post.all()).collect(Collectors.toList());
//
//           return responseDtos;
    }

    @Transactional
    public ResponseEntity<?> getPost(Long id, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();

        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundPost));

        List<Comment> comments = commentRepository.findAllByPostId(id);
        post.updateComments(comments);

        List<PostLike> likes = post.getLikes();
        post.updateLikeCount(likes.size());

        if (postLikeRepository.existsByPostAndAccount(post, account)) {
            post.updateLikeState(true);
        }
        return ResponseEntity.ok(post);
    }
//        Account account = userDetails.getAccount();
//
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("게시글 없음"));
//
//        List<PostLike> likes = post.getLikes();
//        post.updateLikeCount(likes.size());
//
//        if (postLikeRepository.existsByPostAndAccount(post, account)) {
//            post.updateLikeState(true);
//        }
//
//        return new PostResponseDto(post);

    //    @Transactional
//    public ResponseEntity<?> createPost(PostRequestDto requestDto,
//                                        UserDetailsImpl userDetails) throws IOException {
//
//        Account account = userDetails.getAccount();
//
//        String imgPath = s3Uploader.uploadFiles(requestDto.getImage(), "static");
//
//        Post post = new Post(requestDto, account, imgPath);
//
//        postRepository.save(post);
//
//        return ResponseEntity.ok(post);
//
//    }
    @Transactional
    public ResponseEntity<?> createPost(MultipartFile image, String content,
                                        UserDetailsImpl userDetails) throws IOException {

        Account account = userDetails.getAccount();

        if (!(image == null)) {
            String imgPath = s3Uploader.uploadFiles(image, "static");
            Post post = new Post(content, account, imgPath);
            postRepository.save(post);
            return ResponseEntity.ok(post);
        } else {
            Post post = new Post(content, account);
            postRepository.save(post);
            return ResponseEntity.ok(post);
        }

        //       String imgPath = s3Uploader.uploadFiles(image, "static");


    }

    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("글 없음"));

        if (!account.getEmail().equals(post.getUserEmail())) {
            throw new RuntimeException("작성자 아님");
        }
        post.updatePost(requestDto.getContent());
//        postRepository.save(post);

        return post;

    }
    //유도원 바보

    @Transactional
    public ResponseEntity<String> deletePost(Long id, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("글 없음"));

        if (!account.getEmail().equals(post.getUserEmail())) {
            throw new RuntimeException("작성자 아님");
        }

        postRepository.deleteById(id);

        return ResponseEntity.ok("삭제 완료");
    }
}
