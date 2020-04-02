package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;

public class CensusDao {
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;
    public String stateCode;
    public int srNo;
    public int tin;

    public CensusDao(CSVStateCensus csvStateCensus) {
        state = csvStateCensus.getStateName();
        areaInSqKm = csvStateCensus.getAreaPerKm();
        densityPerSqKm = csvStateCensus.getDensityPerSqKm();
        population = csvStateCensus.getPopulation();
    }

    public CensusDao(CsvStateCode csvStateCode) {
        stateCode = csvStateCode.getStateCode();
        srNo = csvStateCode.getSrNo();
        state = csvStateCode.getStateName();
        tin = csvStateCode.getTin();
    }
}
