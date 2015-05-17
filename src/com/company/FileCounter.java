package com.company;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCounter {

    static Logger logger = Logger.getLogger("LOG");

    static ArrayList<ResultOfCounting> results = new ArrayList<>();
    static int sequenceNomber;

    public static Set<File> readFromFile(File file) {
        Set<File> directories = new LinkedHashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                directories.add(new File(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Error while reading file");
        }
        return directories;
    }

    public static synchronized void writeToFile(String outputFile) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(outputFile));
            for (ResultOfCounting item : results) {
                fileWriter.write(item.toString() + "\n");
                displayResults(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void countAllElements(Set<File> set) {
        for (File item : set) {
            try {
                results.add(new ThreadCount(item, new ResultOfCounting(item.getAbsolutePath())).call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayResults(ResultOfCounting resultOfCounting) {
        Formatter formatter = new Formatter();
        System.out.println(formatter.format("|%5d|%5d|%40s|", ++sequenceNomber, resultOfCounting.getNumberOfFiles(), resultOfCounting.getDirectory()));
    }

}
