package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class UsCensusData {
    @CsvBindByName(column = "State Id", required = true)
    private String stateId;

    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Population")
    private Integer population;

    @CsvBindByName(column = "Housing units", required = true)
    private Integer housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    private Double area;

    @CsvBindByName(column = "Water area", required = true)
    private Double waterArea;

    @CsvBindByName(column = "Land area", required = true)
    private Double landArea;

    @CsvBindByName(column = "Population Density", required = true)
    private Double populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    private Double housingDensity;

    public String getStateId() {
        return stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPopulation() {
        return population;
    }

    public Double getArea() {
        return area;
    }

    public Double getPopulationDensity() {
        return populationDensity;
    }

    public UsCensusData() {

    }

    //Parameterized constructor for initializing instance variables
    public UsCensusData(String stateCode, String state, Integer population, Double area, Double populationDensity) {
        this.stateId = stateCode;
        this.state = state;
        this.population = population;
        this.area = area;
        this.populationDensity = populationDensity;
    }
}
