package org.example.projeto_trainee.exceptions;

public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String msg){
        super(msg);
    }

}
