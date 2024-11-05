package org.example.projeto_trainee.exceptions;

public class AuthenticationException extends RuntimeException {
    AuthenticationException(String msg) {
        super(msg);
    }
}
