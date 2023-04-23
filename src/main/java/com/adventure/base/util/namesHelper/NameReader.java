package com.adventure.base.util.namesHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameReader {

    public List<String> readNamesFromFile(String fileName) throws IOException {

        List<String> names = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            while (reader.ready()) {
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    names.add(line);
                }
            }
        }
        return names;
    }
}
