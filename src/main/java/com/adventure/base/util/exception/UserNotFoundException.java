package com.adventure.base.util.exception;

public class UserNotFoundException extends RuntimeException{
    String nameOrId;

    public UserNotFoundException() {}

   public UserNotFoundException(String nameOrId){
       this.nameOrId = nameOrId;
   }

    public String getName() {
        return nameOrId;
    }

    public void setName(String nameOrId) {
        this.nameOrId = nameOrId;
    }
}
