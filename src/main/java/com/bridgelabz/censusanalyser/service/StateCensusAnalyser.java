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

public class StateCensusAnalyser<E> {

    public enum COUNTRY {INDIA, US}

    //Checking whether file is csv or not and class.
    public HashMap<String, CensusDao> checkCsv(COUNTRY country, String... csvFilePath) throws IOException, CSVBuilderException {

        if (Utils.getFileExtension(new File(csvFilePath[csvFilePath.length - 1])).equals("csv")) {

            switch (csvFilePath.length) {
                case 1:
                    return loadingCensusCsvData(country, csvFilePath);
                default:
                   return loadingCensusCsvData(country, csvFilePath);
            }
        } else {

            throw new CSVBuilderException("no csv file",
                    CSVBuilderException.TypeOfException.NO_CSV_FILE);
        }
    }

    private HashMap<String, CensusDao> loadingCensusCsvData(COUNTRY country, String... csvFilePath) throws IOException, CSVBuilderException {
        CensusAdapter censusDataLoader = CensusAdapterFactory.getCensusData(country);
        return censusDataLoader.loadingCensusData(csvFilePath);
    }

    //Method for sorting state names
    public String getStateWiseSortedCensusJsonData(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No Census Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> stateCodeComparator = Comparator.comparing(censusDaoEntry -> censusDaoEntry.getValue().state);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(stateCodeComparator, hashMap);
        Collection<CensusDao> sortedList = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(sortedList);
        return sortedStateCensusJson;
    }

    //Method for sorting state code
    public String getStateCodeWiseSortedJsonData(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No Census Code Data", CSVBuilderException.TypeOfException.NO_CENSUS_CODE_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> stateCodeComparator = Comparator.comparing(censusDaoEntry -> censusDaoEntry.getValue().stateCode);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(stateCodeComparator, hashMap);
        Collection<CensusDao> sortedList = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(sortedList);
        return sortedStateCensusJson;
    }

    //Method for sorting data on the basis of population
    public String getStateWiseSortedPopulation(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No Population State Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> cencusComparator = Comparator.comparing(censusDaoEntry -> censusDaoEntry.getValue().population);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(cencusComparator, hashMap);
        ArrayList<CensusDao> list = new ArrayList<>(sortedByValue.values());
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    //Method for sorting data on basis of population density
    public String getStateWiseSortedPopulationDensity(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No DensityPerSquareKM State Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> censusComparator = Comparator.comparing(censusDAOEntry -> censusDAOEntry.getValue().densityPerSqKm);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(censusComparator, hashMap);
        ArrayList<CensusDao> list = new ArrayList<>(sortedByValue.values());
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    //Method for sorting data on basis of population density
    public String getStateWiseSortedStateArea(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No DensityPerSquareKM State Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> censusComparator = Comparator.comparing(censusDAOEntry -> censusDAOEntry.getValue().areaInSqKm);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(censusComparator, hashMap);
        ArrayList<CensusDao> list = new ArrayList<>(sortedByValue.values());
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    //Method for sorting us data on basis of population density
    public String getUsDataPopulationStateWiseData(HashMap<String, CensusDao> hashMap) throws CSVBuilderException {
        if (hashMap == null || hashMap.size() == 0)
            throw new CSVBuilderException("No DensityPerSquareKM State Data", CSVBuilderException.TypeOfException.NO_CENSUS_DATA);
        ICSVBuilder csvBuilder = CSVBuilderFactory.getInstance();
        Comparator<Map.Entry<String, CensusDao>> censusComparator = Comparator.comparing(censusDAOEntry -> censusDAOEntry.getValue().population);
        LinkedHashMap<String, CensusDao> sortedByValue = csvBuilder.sorting(censusComparator, hashMap);
        ArrayList<CensusDao> list = new ArrayList<>(sortedByValue.values());
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

}
