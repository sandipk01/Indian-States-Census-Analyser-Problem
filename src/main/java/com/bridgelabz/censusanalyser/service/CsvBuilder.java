package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class CsvBuilder {

    private Reader reader;
    private Class className;

    public CsvBuilder(Reader reader, Class className) {
        this.reader = reader;
        this.className = className;
    }

    public Iterator load() {
        CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                .withType(className)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator iterator = csvToBean.iterator();
        return iterator;
    }
}
