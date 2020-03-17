package com.bridgelabz.censusanalyser.model;

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
}
