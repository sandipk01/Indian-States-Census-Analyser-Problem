package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.UsCensusData;

import java.io.IOException;
import java.util.HashMap;

public class USCensusAdapter extends CensusAdapter {

    //Method for loading us census data
    @Override
    public HashMap<String, CensusDao> loadingCensusData(String... csvFilePath) throws IOException, CSVBuilderException {
        return loadingCensusData(UsCensusData.class, csvFilePath[0]);
    }
}
