package com.tagmaster.codetouch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)  // 상태 코드 400 설정
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

