package com.bridgelabz.censusanalyser.service;

        import com.bridgelabz.censusanalyser.adapter.CensusAdapter;
        import com.bridgelabz.censusanalyser.adapter.CensusAdapterFactory;
        import com.bridgelabz.censusanalyser.dao.CensusDao;
        import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
        import com.bridgelabz.censusanalyser.model.CSVStateCensus;
        import com.bridgelabz.censusanalyser.model.CsvStateCode;
        import com.bridgelabz.censusanalyser.model.UsCensusData;
        import com.bridgelabz.censusanalyser.utils.Utils;
        import com.google.gson.Gson;


        import java.io.File;
        import java.io.IOException;
        import java.util.*;
        import java.util.concurrent.CountDownLatch;
        import java.util.stream.Collectors;

public class StateCensusAnalyser {

    public enum COUNTRY {INDIA, US}
    private COUNTRY country;

    public StateCensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    public enum SortingMode {AREA,STATE,STATE_CODE,DENSITY,POPULATION}

    //Checking whether file is csv or not and class.
    public HashMap<String, CensusDao> checkCsv(String... csvFilePath) throws IOException, CSVBuilderException {

        if (Utils.getFileExtension(new File(csvFilePath[csvFilePath.length - 1])).equals("csv")) {
            return loadingCensusCsvData(csvFilePath);
        } else {
            throw new CSVBuilderException("no csv file",
                    CSVBuilderException.TypeOfException.NO_CSV_FILE);
        }
    }

    //Method for loading csv file data
    private HashMap<String, CensusDao> loadingCensusCsvData(String... csvFilePath) throws IOException, CSVBuilderException {
        CensusAdapter censusDataLoader = CensusAdapterFactory.getCensusData(this.country);
        return censusDataLoader.loadingCensusData(csvFilePath);
    }

    //method for sorting csv file data
    public String getSortedCensusData(HashMap<String,CensusDao> censusDAOMap,SortingMode mode) throws CSVBuilderException {
        if (censusDAOMap == null || censusDAOMap.size() == 0)
            throw new CSVBuilderException("No Census Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ArrayList censusDTO = censusDAOMap.values().stream()
                .sorted(CensusDao.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    //Method to sort data according to population and density.
    public String getSortByPopulationAndDensity(HashMap<String,CensusDao> censusDAOMap) throws CSVBuilderException {
        if (censusDAOMap == null || censusDAOMap.size() == 0)
            throw new CSVBuilderException("No Census Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ArrayList censusDTO = censusDAOMap.values().stream()
                .sorted(Comparator.comparingDouble(CensusDao::getPopulation).thenComparingDouble(CensusDao::getPopulationDensity).reversed())
                .map(c -> c.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

}
