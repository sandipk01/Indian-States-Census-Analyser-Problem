package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CsvStateCode {
    @CsvBindByName(column = "SrNo", required = true)
    private Integer srNo;
    @CsvBindByName(column = "StateName", required = true)
    private String stateName;
    @CsvBindByName(column = "TIN", required = true)
    private Integer tin;
    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public CsvStateCode() {

    }

    public Integer getSrNo() {
        return srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public Integer getTin() {
        return tin;
    }

    public String getStateCode() {
        return stateCode;
    }
}
