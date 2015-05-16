package com.company;

import java.io.File;
import java.util.concurrent.Callable;

public class ThreadCount implements Callable<ResultOfCounting>{
    File item;
    ResultOfCounting resultOfCounting;

    public ThreadCount(File item, ResultOfCounting resultOfCounting) {
        this.item = item;
        this.resultOfCounting = resultOfCounting;
    }

    @Override
    public ResultOfCounting call() throws Exception {
        return countItems(item, resultOfCounting);
    }

    public ResultOfCounting countItems(File item,ResultOfCounting resultOfCounting){
        for (File f : item.listFiles()) {
            resultOfCounting.incrementNumberOfFiles();
            if (f.isDirectory())
                countItems(f, resultOfCounting);
        }
        return resultOfCounting;
    }
}

