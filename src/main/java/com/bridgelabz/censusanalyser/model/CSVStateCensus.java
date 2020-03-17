package com.bridgelabz.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State")
    private String stateName;
    @CsvBindByName(column = "Population")
    private int population;
    @CsvBindByName(column = "AreaInSqKm")
    private int areaPerKm;
    @CsvBindByName(column = "DensityPerSqKm")
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
