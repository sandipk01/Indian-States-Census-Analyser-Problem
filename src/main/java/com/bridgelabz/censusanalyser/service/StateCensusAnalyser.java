package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
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
    public int checkCsv() throws IOException, CSVBuilderException {
        if (Utils.getFileExtension(new File(fileName)).equals("csv"))
            return load();
        else
            throw new CSVBuilderException("no csv file",
                    CSVBuilderException.TypeOfException.NO_CSV_FILE);
    }

    //Loading csv file data
    public int load() throws IOException, CSVBuilderException {
        int counter = 0;
        ICSVBuilder icsvBuilder=CSVBuilderFactory.getInstance();
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            Iterator iterator = icsvBuilder.load(reader, className);
            counter = icsvBuilder.size(iterator);
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found",
                    CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found",
                    CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
        return counter;
    }

}
