package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    private String stateName;
    @CsvBindByName(column = "Population", required = true)
    private Integer population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    private Double areaPerKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private Double densityPerSqKm;
    private String stateCode = new CsvStateCode().getStateCode();

    public CSVStateCensus() {
    }

    //Parameterized constructor for initializing instance variables
    public CSVStateCensus(String stateCode, String stateName, Integer population, Double areaPerKm, Double densityPerSqKm) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.population = population;
        this.areaPerKm = areaPerKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getStateName() {
        return stateName;
    }

    public Integer getPopulation() {
        return population;
    }

    public Double getAreaPerKm() {
        return areaPerKm;
    }

    public Double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public String getStateCode() {
        return stateCode;
    }
}
