package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CsvStateCode {
    @CsvBindByName(column = "SrNo")
    private int srNo;
    @CsvBindByName(column = "StateName")
    private String stateName;
    @CsvBindByName(column = "TIN")
    private int tin;
    @CsvBindByName(column = "StateCode")
    private String stateCode;
}
