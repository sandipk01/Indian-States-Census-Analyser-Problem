package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser<E> {

    private String fileName;
    private final Class<E> className;

    public StateCensusAnalyser(String fileName, Class<E> className) {
        this.fileName = fileName;
        this.className = className;
    }

    //Checking whether file is csv or not.
    public <S,T> HashMap<S,T> checkCsv() throws IOException, CSVBuilderException {
        if (Utils.getFileExtension(new File(fileName)).equals("csv"))
            return load();
        else
            throw new CSVBuilderException("no csv file",
                    CSVBuilderException.TypeOfException.NO_CSV_FILE);
    }

    //Loading csv file data
    public <S,T> HashMap<S,T> load() throws IOException, CSVBuilderException {
        HashMap<S,T> hashMap= null;
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            hashMap = csvBuilder.loadHashMap(reader, className);
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
        return hashMap;
    }

    //Getting sorted Json data
    public <S,T> String getSortedJsonData(Comparator<Map.Entry<S, T>> comparator, HashMap<S, T> userData){
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        HashMap<S, T> sortedByValue = csvBuilder.sortingList(comparator,userData);
        Collection<T> records= sortedByValue.values();
        String sortedJson=new Gson().toJson(records);
        return sortedJson;
    }
}
