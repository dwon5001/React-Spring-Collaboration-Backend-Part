package com.example.ReactSpringCollaborationProject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NotFoundPost(HttpStatus.NOT_FOUND.value(), "P001", "게시물을 찾을 수 없습니다."),
    NotFoundComment(HttpStatus.NOT_FOUND.value(), "P001", "댓글을 찾을 수 없습니다.");


    private final int httpStatus;
    private final String errorCode;
    private final String message;

}