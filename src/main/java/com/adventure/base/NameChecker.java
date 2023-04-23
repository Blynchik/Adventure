package com.adventure.base;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class NameChecker {

    public static void main(String[] args) {
        String src = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\wet.txt";
        String firstName = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\firstName.txt";
        String lastName = "C:\\Users\\Blynchik\\Desktop\\base\\base\\src\\main\\java\\com\\adventure\\base\\lastName.txt";

        try (FileReader fileReader = new FileReader(src);
             BufferedReader reader = new BufferedReader(fileReader);
             FileWriter nameWriter = new FileWriter(firstName);
             FileWriter surnameWriter = new FileWriter(lastName)) {

            Set<String> uniqueNames = new HashSet<>();
            Set<String> uniqueSurnames = new HashSet<>();

            while (reader.ready()) {
                String line = reader.readLine();

                if (line.isEmpty()) {
                    continue;
                }

                String[] fullNames = line.split(" – ");

                String[] namesAndSurnames = fullNames[0].split(" ");

                for (int i = 0; i < namesAndSurnames.length; i++) {

                    if (i < namesAndSurnames.length - 1 || namesAndSurnames.length == 1) {
                        uniqueNames.add(namesAndSurnames[i]);
                    } else {
                        uniqueSurnames.add(namesAndSurnames[i]);
                    }
                }
            }

            nameWriter.write("insert into first_name(first_name) \n" +
                    "values ");
            for (String s : uniqueNames) {
                nameWriter.write("('" + s + "'),\n");
            }

            surnameWriter.write("insert into last_name(last_name) \n" +
                    "values ");
            for (String s : uniqueSurnames) {
                surnameWriter.write("('" + s + "'),\n");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
