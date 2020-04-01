package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.dao.IndianCensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

public class CensusDataLoader {
    //Loading Census csv file data
    public static HashMap loadingCsvStateCodeData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, IndianCensusDao> censusDaoHashMap = new HashMap<>();
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
    public static HashMap<String, IndianCensusDao> loadingCensusCsvData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, IndianCensusDao> censusDaoHashMap = new HashMap<>();
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
}
