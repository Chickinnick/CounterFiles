package com.company;

import java.io.File;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        String sourceFile = args[0];
        String outputFile = args[1];

        Set<File> setOfDirs = FileCounter.readFromFile(new File(sourceFile));
        FileCounter.countAllElements(setOfDirs);
        FileCounter.writeToFile(outputFile);

    }
}
