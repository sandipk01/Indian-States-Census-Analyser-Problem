package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.model.UsCensusData;

public class CensusDao {
    public String state;
    public Double areaInSqKm;
    public Double densityPerSqKm;
    public Integer population;
    public String stateCode;
    public Integer srNo;
    public Integer tin;

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

    public CensusDao(UsCensusData usCensusData){
        stateCode=usCensusData.getStateId();
        areaInSqKm=usCensusData.getArea();
        state=usCensusData.getState();
        densityPerSqKm=usCensusData.getPopulationDensity();
        population=usCensusData.getPopulation();
    }
}
