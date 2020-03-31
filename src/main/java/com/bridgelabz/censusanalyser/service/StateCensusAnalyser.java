package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.dao.IndianCensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.utils.Utils;
import com.google.gson.Gson;
import sun.awt.image.ImageWatched;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser<E> {

    //Checking whether file is csv or not and class.
    public HashMap checkCsv(String filePath, Class<E> className) throws IOException, CSVBuilderException {
        if (Utils.getFileExtension(new File(filePath)).equals("csv") && className == CSVStateCensus.class)
            return loadingCensusCsvData(filePath);
        else if (Utils.getFileExtension(new File(filePath)).equals("csv") && className == CsvStateCode.class)
            return loadingCsvStateCodeData(filePath);
        else
            throw new CSVBuilderException("no csv file",
                    CSVBuilderException.TypeOfException.NO_CSV_FILE);
    }

    //Loading Census csv file data
    public HashMap loadingCsvStateCodeData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, IndianCensusDao> censusDaoHashMap = new HashMap<String, IndianCensusDao>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<CsvStateCode> csvIterator = csvBuilder.loadFromIterator(reader, CsvStateCode.class);
            while (csvIterator.hasNext()) {
                IndianCensusDao indianCensusDAO = new IndianCensusDao(csvIterator.next());
                censusDaoHashMap.put(indianCensusDAO.state, indianCensusDAO);
            }
            return censusDaoHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }

    //Loading state code csv file data
    public HashMap<String, IndianCensusDao> loadingCensusCsvData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, IndianCensusDao> censusDaoHashMap = new HashMap<String, IndianCensusDao>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<CSVStateCensus> csvIterator = csvBuilder.loadFromIterator(reader, CSVStateCensus.class);
            while (csvIterator.hasNext()) {
                IndianCensusDao indianCensusDAO = new IndianCensusDao(csvIterator.next());
                censusDaoHashMap.put(indianCensusDAO.state, indianCensusDAO);
            }
            return censusDaoHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }

    //Getting sorted Json data
    public <S, T> String getSortedJsonData(Comparator<Map.Entry<S, T>> comparator, HashMap<S, T> userData) {
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        LinkedHashMap<S, T> sortedByValue = csvBuilder.sorting(comparator, userData);
        Collection<T> records = sortedByValue.values();
        String sortedJson = new Gson().toJson(records);
        return sortedJson;
    }
}
