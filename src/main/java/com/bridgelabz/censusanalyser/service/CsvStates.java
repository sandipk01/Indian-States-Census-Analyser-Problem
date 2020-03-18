package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CsvStates {

    private String filename;

    public CsvStates(String filename) {
        this.filename = filename;
    }

    public int loadCsvState() throws IOException {
        int counter = 0;
        Reader reader = Files.newBufferedReader(Paths.get(filename));
        CsvToBean<CsvStateCode> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvStateCode.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator iterator = csvToBean.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        return counter;
    }
}
