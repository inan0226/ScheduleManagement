package com.example.schedulemanagement.Exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("비밀번호 맞지 않습니다.");
    }
}
