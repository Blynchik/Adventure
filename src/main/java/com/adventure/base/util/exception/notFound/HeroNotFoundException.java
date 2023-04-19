package com.adventure.base.util.exception.notFound;

public class HeroNotFoundException extends NotFoundException {

    public HeroNotFoundException() {
    }

    public HeroNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
