package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.service.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.service.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    //Loading StateCensus or StateCode csv file data
    @Override
    public HashMap<String, CensusDao> loadingCensusData(String... csvFilePath) throws CSVBuilderException, IOException {
        if (csvFilePath.length == 1)
            return loadingCensusData(CSVStateCensus.class, csvFilePath[0]);;
        return loadStateCodeData(csvFilePath[1]);
    }

    //Method to loading StateCode data
    public static HashMap<String, CensusDao> loadStateCodeData(String getPath) throws CSVBuilderException, IOException {
        HashMap<String, CensusDao> censusDaoHashMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))) {
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
}
