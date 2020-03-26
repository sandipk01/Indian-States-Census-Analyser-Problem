package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    private String stateName;
    @CsvBindByName(column = "Population", required = true)
    private int population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    private int areaPerKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private int densityPerSqKm;

    public String getStateName() {
        return stateName;
    }

    public int getPopulation() {
        return population;
    }

    public int getAreaPerKm() {
        return areaPerKm;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }
}
