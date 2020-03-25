package com.bridgelabz.censusanalyser.service;

public class CSVBuilderFactory {
    
    //Get the instance object
    public static ICSVBuilder getInstance(){
        return new CsvBuilder();
    }
}
