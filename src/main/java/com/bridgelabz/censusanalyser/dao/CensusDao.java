package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.model.UsCensusData;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;

import java.util.Comparator;

public class CensusDao {
    public String state;
    public Double areaInSqKm;
    public Double densityPerSqKm;
    public Integer population;
    public String stateCode;
    public Integer srNo;
    public Integer tin;

    //Constructor for initializing csv state data
    public CensusDao(CSVStateCensus csvStateCensus) {
        state = csvStateCensus.getStateName();
        areaInSqKm = csvStateCensus.getAreaPerKm();
        densityPerSqKm = csvStateCensus.getDensityPerSqKm();
        population = csvStateCensus.getPopulation();
    }
    //Constructor for initializing csv state code data
    public CensusDao(CsvStateCode csvStateCode) {
        stateCode = csvStateCode.getStateCode();
        srNo = csvStateCode.getSrNo();
        state = csvStateCode.getStateName();
        tin = csvStateCode.getTin();
    }

    //Constructor for initializing csv us state data
    public CensusDao(UsCensusData usCensusData) {
        stateCode = usCensusData.getStateId();
        areaInSqKm = usCensusData.getArea();
        state = usCensusData.getState();
        densityPerSqKm = usCensusData.getPopulationDensity();
        population = usCensusData.getPopulation();
    }

    //Method for comparing state,state_code,population,density,area wise and returning comparator
    public static Comparator<? super CensusDao> getSortComparator(StateCensusAnalyser.SortingMode mode) {
        if (mode.equals(StateCensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SortingMode.STATE_CODE))
            return Comparator.comparing(census -> census.stateCode);
        if (mode.equals(StateCensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CensusDao::getPopulation).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CensusDao::getPopulationDensity).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CensusDao::getTotalArea).reversed();
        return null;
    }

    public double getTotalArea() {
        return areaInSqKm;
    }

    public double getPopulationDensity() {
        return this.densityPerSqKm;
    }

    public double getPopulation() {
        return this.population;
    }


    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new CSVStateCensus(stateCode, state, population, areaInSqKm, densityPerSqKm);
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new UsCensusData(stateCode,state, population, areaInSqKm, densityPerSqKm);
        return null;
    }
}
