package com.example.ReactSpringCollaborationProject.post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostRequestDto {

    private String content;

//    private MultipartFile image;   // @modelattribute 사용시 활용

}
