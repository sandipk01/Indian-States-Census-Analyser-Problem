package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.dao.IndianCensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
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

    //Method for sorting state names
    public String getStateWiseSortedCensusJsonData(HashMap<String, IndianCensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No Census Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, IndianCensusDao>> stateCodeComparator = Comparator.comparing(censusDaoEntry -> censusDaoEntry.getValue().state);
        LinkedHashMap<String, IndianCensusDao> sortedByValue = csvBuilder.sorting(stateCodeComparator, hashMap);
        Collection<IndianCensusDao> sortedList = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(sortedList);
        return sortedStateCensusJson;
    }

    //Method for sorting state code
    public String getStateWiseSortedJsonStateCode(HashMap<String, IndianCensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No Census Code Data", CSVBuilderException.TypeOfException.NO_CENSUS_CODE_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, IndianCensusDao>> stateCodeComparator = Comparator.comparing(censusDaoEntry -> censusDaoEntry.getValue().stateCode);
        LinkedHashMap<String, IndianCensusDao> sortedByValue = csvBuilder.sorting(stateCodeComparator, hashMap);
        Collection<IndianCensusDao> sortedList = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(sortedList);
        return sortedStateCensusJson;
    }


}
