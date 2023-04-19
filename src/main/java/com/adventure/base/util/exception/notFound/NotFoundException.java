package com.adventure.base.util.exception.notFound;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
    }

    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}
