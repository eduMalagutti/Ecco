package org.example.projeto_trainee.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String msg){
        super(msg);
    }
}
