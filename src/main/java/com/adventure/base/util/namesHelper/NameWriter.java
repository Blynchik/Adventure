package com.adventure.base.util.namesHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class NameWriter {

    public void writeNamesToFile(List<String> names, String fileName, String table) throws IOException {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write("insert into " + table + " \n" +
                    "values ");

            for (String s : names) {
                fileWriter.write("('" + s + "'),\n");
            }
        }
    }
}

