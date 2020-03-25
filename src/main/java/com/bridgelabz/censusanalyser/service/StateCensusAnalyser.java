package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.utils.Utils;

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

    //Checking whether file is csv or not.
    public int checkCsv() throws IOException, StateCensusAnalyserException {
        if (Utils.getFileExtension(new File(fileName)).equals("csv"))
            return load();
        else
            throw new StateCensusAnalyserException("no csv file",
                    StateCensusAnalyserException.TypeOfException.NO_CSV_FILE);
    }

    //Loading csv file data
    public int load() throws IOException, StateCensusAnalyserException {
        int counter = 0;
        ICSVBuilder icsvBuilder=CSVBuilderFactory.getInstance();
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            Iterator iterator = icsvBuilder.load(reader, className);
            counter = icsvBuilder.size(iterator);
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException("No such file found",
                    StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException("No such field found",
                    StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
        return counter;
    }

}
