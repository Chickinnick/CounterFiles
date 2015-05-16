package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Main implements Runnable{
    static Thread mainThread = new Thread(new Main());
    public static boolean isInterrupted = false;

    public static void main(String[] args) {

        String sourceFile = args[0];
        String outputFile = args[1];

        mainThread.start();

        Set<File> setOfDirs = FileCounter.readFromFile(new File(sourceFile));
        FileCounter.countAllElements(setOfDirs);
        FileCounter.writeToFile(outputFile);
    }

    @Override
    public void run() {

        while (true) {
            try {
                if (System.in.read() == 'q') {
                    isInterrupted = true;
                    System.out.println("break");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
