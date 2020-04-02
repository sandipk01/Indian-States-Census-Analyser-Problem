package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.dao.CensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.model.UsCensusData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusDataLoader {

    //Loading Census csv file data
    public static HashMap loadingCsvStateCodeData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, CensusDao> censusDaoHashMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<CsvStateCode> csvIterator = csvBuilder.loadFromIterator(reader, CsvStateCode.class);
            Iterable<CsvStateCode> csvStateCodeIterable = () -> csvIterator;
            StreamSupport.stream(csvStateCodeIterable.spliterator(), false)
                    .map(CsvStateCode.class::cast)
                    .forEach(CsvStateCode -> censusDaoHashMap.put(CsvStateCode.getStateName(), new CensusDao(CsvStateCode)));
            return censusDaoHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }

    //Loading state code csv file data
    public static HashMap<String, CensusDao> loadingCensusCsvData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, CensusDao> censusDaoHashMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<CSVStateCensus> csvIterator = csvBuilder.loadFromIterator(reader, CSVStateCensus.class);
            Iterable<CSVStateCensus> csvStateCensusIterable = () -> csvIterator;
            StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                    .map(CSVStateCensus.class::cast)
                    .forEach(CSVStateCensus -> censusDaoHashMap.put(CSVStateCensus.getStateName(), new CensusDao(CSVStateCensus)));
            return censusDaoHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }

    //Loading state code csv file data
    public static HashMap<String, CensusDao> loadingUsCensusCsvData(String filePath) throws IOException, CSVBuilderException {
        HashMap<String, CensusDao> censusDaoHashMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<UsCensusData> csvIterator = csvBuilder.loadFromIterator(reader, UsCensusData.class);
            Iterable<UsCensusData> csvUsCensusIterable = () -> csvIterator;
            StreamSupport.stream(csvUsCensusIterable.spliterator(), false)
                    .map(UsCensusData.class::cast)
                    .forEach(UsCensusData -> censusDaoHashMap.put(UsCensusData.getState(), new CensusDao(UsCensusData)));
            return censusDaoHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("No such file found", CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("No such field found", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }
}
