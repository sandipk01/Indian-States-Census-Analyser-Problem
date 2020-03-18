package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CsvStateCode {
    @CsvBindByName(column = "SrNo", required = true)
    private int srNo;
    @CsvBindByName(column = "StateName", required = true)
    private String stateName;
    @CsvBindByName(column = "TIN", required = true)
    private int tin;
    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;
}
