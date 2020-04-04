package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;

public class CensusAdapterFactory {

    //Factory method to get india or us data
    public static CensusAdapter getCensusData(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new USCensusAdapter();
        return null;
    }
}
