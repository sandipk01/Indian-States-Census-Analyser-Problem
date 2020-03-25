package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
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

public class StateCensusAnalyser {

    private String fileName;
    private Class className;

    public StateCensusAnalyser(String fileName, Class className) {
        this.fileName = fileName;
        this.className = className;
    }

    public int checkCsv() throws IOException, StateCensusAnalyserException {
        if (Utils.getFileExtension(new File(fileName)).equals("csv"))
            return load();
        else
            throw new StateCensusAnalyserException("no csv file", StateCensusAnalyserException.TypeOfException.NO_CSV_FILE);
    }

    public int load() throws IOException, StateCensusAnalyserException {
        int counter = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            CsvBuilder csvBuilder = new CsvBuilder(reader, className);
            Iterator iterator = csvBuilder.load();
            while (iterator.hasNext()) {
                iterator.next();
                counter++;
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException("No such file found", StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException("No such field found", StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
        return counter;
    }

}
