package br.com.udemy.microservices.exceptions;

public class UserServiceException extends RuntimeException{
    public UserServiceException(String message){
        super(message);
    }
}
