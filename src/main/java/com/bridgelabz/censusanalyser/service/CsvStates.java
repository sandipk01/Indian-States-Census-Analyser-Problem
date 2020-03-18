package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.utils.Utils;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CsvStates {

    private String filename;

    public CsvStates(String filename) {
        this.filename = filename;
    }

    public int loadCsvState() throws IOException, StateCensusAnalyserException {
        int counter = 0;
        try {
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
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException("No such file found", StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        }
        return counter;
    }
}
