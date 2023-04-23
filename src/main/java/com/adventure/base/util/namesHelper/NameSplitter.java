package com.adventure.base.util.namesHelper;

public class NameSplitter {

    public String[] splitFullName(String fullName) {
        String[] fullNames = fullName.split(" – ");
        return fullNames[0].split(" ");
    }
}
