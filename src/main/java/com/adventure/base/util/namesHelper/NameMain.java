package com.adventure.base.util.namesHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NameMain {

    public static void main(String[] args) {

        String src = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\util\\namesHelper\\wet.txt";
        String firstName = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\util\\namesHelper\\firstName.txt";
        String lastName = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\util\\namesHelper\\lastName.txt";
        String  nameTable = "first_name(first_name)";
        String  surnameTable = "last_name(last_name)";

        NameReader nameReader = new NameReader();
        NameSplitter nameSplitter = new NameSplitter();
        NameWriter firstNameWriter = new NameWriter();
        NameWriter lastNameWriter = new NameWriter();

        try {
            List<String> fullNames = nameReader.readNamesFromFile(src);

            Set<String> uniqueNames = new HashSet<>();
            Set<String> uniqueSurnames = new HashSet<>();

            for (String fullName : fullNames) {
                String[] namesAndSurnames = nameSplitter.splitFullName(fullName);
                for (int i = 0; i < namesAndSurnames.length; i++) {
                    if (i < namesAndSurnames.length - 1 || namesAndSurnames.length == 1) {
                        uniqueNames.add(namesAndSurnames[i]);
                    } else {
                        uniqueSurnames.add(namesAndSurnames[i]);
                    }
                }
            }

            firstNameWriter.writeNamesToFile(new ArrayList<>(uniqueNames), firstName,nameTable);
            lastNameWriter.writeNamesToFile(new ArrayList<>(uniqueSurnames), lastName, surnameTable);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

