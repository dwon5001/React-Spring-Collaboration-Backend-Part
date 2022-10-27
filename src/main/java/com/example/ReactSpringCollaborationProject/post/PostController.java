package com.example.ReactSpringCollaborationProject.post;

import com.example.ReactSpringCollaborationProject.global.dto.ResponseDto;
import com.example.ReactSpringCollaborationProject.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

//    프론트에서 요청 받는 형식
//    @PostMapping
//    public ResponseEntity<?> createPost(@ModelAttribute PostRequestDto requestDto,
//                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
//
//        return ResponseEntity.ok(postService.createPost(requestDto, userDetails));
//    }
    @PostMapping(consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<?> createPost(@RequestPart(required = false, name = "image") MultipartFile multipartFile,
                                  @RequestParam(required = false) String content,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        return ResponseDto.success(postService.createPost(multipartFile, content, userDetails));
    }

    @GetMapping
    public ResponseDto<?> getAllPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(postService.getAllPosts(userDetails));
    }

    @GetMapping("/{id}") // 게시글 하나 조회
    public ResponseDto<?> getPost(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails)  {
        return ResponseDto.success(postService.getPost(id, userDetails));
    }

    @PutMapping("/{id}")
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(postService.updatePost(id, requestDto, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(postService.deletePost(id, userDetails));
    }
}



