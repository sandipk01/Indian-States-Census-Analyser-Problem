package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.UsCensusData;
import com.bridgelabz.censusanalyser.service.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.service.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    //Abstract method for loading census data
    public abstract HashMap<String, CensusDao> loadingCensusData(String... csvFilePath) throws CSVBuilderException, IOException;

    //Method to loading india data or us data
    public <E> HashMap<String, CensusDao> loadingCensusData(Class<E> censusCSVClass, String... getPath) throws CSVBuilderException, IOException {
        HashMap<String, CensusDao> censusDAOMap = new HashMap();
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath[0]))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
            Iterator<E> csvFileIterator = csvBuilder.loadFromIterator(reader, censusCSVClass);
            Iterable<E> csvStateCensusIterable = () -> csvFileIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.model.CSVStateCensus"))
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(CSVStateCensus -> censusDAOMap.put(CSVStateCensus.getStateName(), new CensusDao(CSVStateCensus)));
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.model.UsCensusData"))
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(UsCensusData.class::cast)
                        .forEach(usCensusData -> censusDAOMap.put(usCensusData.getState(), new CensusDao(usCensusData)));
            return censusDAOMap;
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Number of data fields does not match number of headers.",
                    CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER);
        }
    }
}
