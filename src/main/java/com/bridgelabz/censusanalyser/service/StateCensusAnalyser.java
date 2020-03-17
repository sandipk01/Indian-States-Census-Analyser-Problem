package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    private String fileName;

    public StateCensusAnalyser(String fileName) {
        this.fileName = fileName;
    }

    public Iterator load() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CSVStateCensus.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator iterator = csvToBean.iterator();
        return iterator;
    }

    public int size() throws StateCensusAnalyserException, IOException {
        int counter = 0;
        Iterator itr = null;
        try {
            itr = load();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException("No such file found", StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        }
        while (itr.hasNext()) {
            counter++;
            itr.next();
        }
        return counter;
    }

}
